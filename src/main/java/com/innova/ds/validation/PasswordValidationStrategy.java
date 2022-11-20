package com.innova.ds.validation;

import com.innova.ds.constant.ErrorMsgType;
import com.innova.ds.constant.ValidationType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.utils.StringUtils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public enum PasswordValidationStrategy implements ValidationStrategy {

    NO_SEQUENCE (ValidationType.NO_SEQUENCE, ErrorMsgType.NO_SEQUENCE) {
        @Override
        public <T extends BaseInput> Optional<Boolean> validate(T input) {
            String inputString = input.getPassword();
            int matches = StringUtils.executeRegex("(.+)\\1", inputString);
            return Optional.of(matches == 0);
        }
    },
    MIN_NUMERIC (ValidationType.MIN_NUMERIC, ErrorMsgType.MIN_NUMERIC) {
        @Override
        public <T extends BaseInput> Optional<Boolean> validate(T input) {
            AtomicInteger numericCnt = new AtomicInteger();
            for(char node : input.getPassword().toCharArray()) {
                if(Character.isDigit(node)) {
                    numericCnt.getAndIncrement();
                }
            }
            return Optional.ofNullable(numericCnt.intValue() >= MIN_NUMERIC_NUM);
        }
    },
    MIN_LOWERCASE (ValidationType.MIN_LOWERCASE, ErrorMsgType.MIN_LOWERCASE) {
        @Override
        public <T extends BaseInput> Optional<Boolean> validate(T input) {
            AtomicInteger lowerCnt = new AtomicInteger();
            for(char node : input.getPassword().toCharArray()){
                if(Character.isLowerCase(node)) {
                    lowerCnt.getAndIncrement();
                }
            }
            return Optional.ofNullable(lowerCnt.intValue() >= MIN_LOWERCASE_NUM);
        }
    },
    LOWERCASE_NUMERIC_ONLY (ValidationType.LOWERCASE_NUMERIC_ONLY, ErrorMsgType.LOWERCASE_NUMERIC_ONLY) {
        @Override
        public <T extends BaseInput> Optional<Boolean> validate(T input) {
            AtomicInteger cnt = new AtomicInteger();
            for(char node : input.getPassword().toCharArray()) {
                if(!Character.isDigit(node) && !Character.isLowerCase(node)) {
                    cnt.getAndIncrement();
                }
            }
            return Optional.ofNullable(cnt.intValue() == 0);
        }
    },
    LENGTH_RANGE (ValidationType.LENGTH_RANGE, ErrorMsgType.LENGTH_RANGE) {
        @Override
        public <T extends BaseInput> Optional<Boolean> validate(T input) {
            String password = input.getPassword();
            return Optional.ofNullable(password.length() <= MAX_LENGTH && password.length() >= MIN_LENGTH);
        }
    };

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 12;
    private static final int MIN_NUMERIC_NUM = 1;
    private static final int MIN_LOWERCASE_NUM = 1;
    private ValidationType validationType;
    private ErrorMsgType error;

    PasswordValidationStrategy(ValidationType validationType, ErrorMsgType error) {
        this.validationType = validationType;
        this.error = error;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public String getDefaultErrorMsg() {
        return error.getDescription();
    }
}
