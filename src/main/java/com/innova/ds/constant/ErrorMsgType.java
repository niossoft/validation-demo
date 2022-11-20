package com.innova.ds.constant;

public enum ErrorMsgType {
    LENGTH_RANGE(0, "Failed length requirement."),
    MIN_LOWERCASE(1, "must contain at least one alphabetical lowercase character."),
    MIN_NUMERIC(2, "must contain at least one numeric character."),
    LOWERCASE_NUMERIC_ONLY(3, "alphabetical lowercase & numeric character only."),
    NO_SEQUENCE(4, "must not contain any repeating substrings of two characters or more"),
    UNSUPPORTED(9999, "unsupported");

    private final int code;
    private final String description;

    private ErrorMsgType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
