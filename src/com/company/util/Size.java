package com.company.util;

public enum Size {

    OBJECT_HEADER(8L),
    OBJECT_REFERENCE(8L),
    ARRAY_HEADER(12L),
    STRING_HEADER(32L);

    private long value;

    Size(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
