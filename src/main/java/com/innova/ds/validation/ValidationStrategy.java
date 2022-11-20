package com.innova.ds.validation;

import com.innova.ds.constant.ValidationType;
import com.innova.ds.dto.BaseInput;

import java.util.Optional;

public interface ValidationStrategy {

    <T extends BaseInput> Optional<Boolean> validate(T input);
    String getDefaultErrorMsg();
    ValidationType getValidationType();
}
