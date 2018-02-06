package com.company;

import com.company.util.Const;
import com.company.util.Helper;
import com.company.util.Size;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ObjectSize {

    private final Object lock = new Object();

    /* The method to implement, it returns the number of bytes. */
    public long getSize(Object object) {
        /* Implement me */

        synchronized (lock) {
            long objectSize = Size.OBJECT_HEADER.getValue();

            Class clazz = object.getClass();
            List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

            objectSize += fields.stream().filter(f -> f.getType().isPrimitive()).mapToLong(f -> {
                f.setAccessible(true);
                return Helper.getPrimitives().get(f.getType().getName());
            }).sum();

            fields.stream().filter(f -> f.getType().isArray() && f.getType().getName().startsWith("[[")).forEach(f -> {

            });

            objectSize += fields.stream().filter(f -> f.getType().isArray()).mapToLong(f -> {
                f.setAccessible(true);
                return getArraySize(object, f);
            }).sum();

            objectSize += fields.stream().filter(f -> !f.getType().isPrimitive() && !f.getType().isArray()).mapToLong(f -> {
                f.setAccessible(true);
                return getNonPrimitiveObjectSize(f, object);
            }).sum();

            return Helper.roundUp(objectSize);
        }
    }

    private long getArraySize(Object object, Field f) {
        try {
            Class<?> fieldType = f.getType();
            // add checking for multidimensional array
            if (Helper.getPrimitives().containsKey(fieldType.getComponentType().getTypeName())) {
                String typeName = fieldType.getComponentType().getTypeName();
                return Size.ARRAY_HEADER.getValue() + Array.getLength(f.get(object)) * Helper.getPrimitives().get(typeName);
            } else {
                return Size.ARRAY_HEADER.getValue() + Helper.sizeCalculator(Size.ARRAY_HEADER.getValue(), Size.OBJECT_REFERENCE.getValue(), Array.getLength(f.get(object)));
            }
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    private long getMultidimensionalArraySize(Field f, Object sourceObject) {
        try {
            int length = Array.getLength(f.get(sourceObject));
            Object o = f.get(sourceObject);
            IntStream.range(0, length - 1).forEach(i -> {
                Array.get(o, i);
            });
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    private long getNonPrimitiveObjectSize(Field f, Object object) {
        try {
            Class<?> fieldType = f.getType();
            if (Const.STRING_CLASS.getValue().equals(fieldType.getTypeName())) {
                String value = (String) f.get(object);
                return Helper.sizeCalculator(Size.STRING_HEADER.getValue(), Helper.getStringMultiplier(), value.length());
            } else {
                return getObjectSize(fieldType);
            }
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    private long getObjectSize(Class<?> fieldType) {
        String fieldTypeName = fieldType.getTypeName();
        if (Helper.isWrapper(fieldTypeName)) {
            return Size.OBJECT_HEADER.getValue() + Helper.getWrappers().get(fieldTypeName);
        } else {
            return Size.OBJECT_HEADER.getValue();
        }
    }

}
