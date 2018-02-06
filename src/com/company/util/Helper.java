package com.company.util;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    private static Map<String, Long> primitives = new HashMap<String, Long>() {{
        put("boolean", 1L);
        put("byte", 1L);
        put("char", 2L);
        put("short", 2L);
        put("int", 4L);
        put("float", 4L);
        put("long", 8L);
        put("double", 8L);
    }};

    private static Map<String, Long> wrappers = new HashMap<String, Long>() {{
        put("java.lang.Boolean", 1L);
        put("java.lang.Byte", 1L);
        put("java.lang.Character", 2L);
        put("java.lang.Short", 2L);
        put("java.lang.Integer", 4L);
        put("java.lang.Float", 4L);
        put("java.lang.Long", 8L);
        put("java.lang.Double", 8L);
    }};

    private static final long STRING_MULTIPLIER = 2L;

    public static long roundUp(long n) {
        return (n + 7) / 8 * 8;
    }

    public static long sizeCalculator(long header, long multiplier, long objectLength) {
        return header + multiplier * objectLength;
    }

    public static Map<String, Long> getPrimitives() {
        return primitives;
    }

    public static Map<String, Long> getWrappers() {
        return wrappers;
    }

    public static long getStringMultiplier() {
        return STRING_MULTIPLIER;
    }

    public static boolean isWrapper(String fieldTypeName) {
        return wrappers.containsKey(fieldTypeName);
    }
}
