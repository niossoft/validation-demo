package com.innova.ds.constant;

public enum RuleType {

    LOWERCASE_NUMERIC_ONLY(0, "Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each."),
    SEQUENCE(1, "must not contain any repeating substrings of one characters or more."),
    LENGTH(2, "Must be between 5 and 12 characters in length.");

    private final int code;
    private final String description;

    RuleType(int code, String description) {
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
