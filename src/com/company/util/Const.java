package com.company.util;

public enum Const {
    STRING_CLASS("java.lang.String");

    private String value;

    Const(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
