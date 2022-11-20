package com.innova.ds.validation.context;

import com.innova.ds.dto.BaseInput;
import com.innova.ds.validation.ValidationStrategy;

import java.util.*;

public class ValidationContext {

    private Set<ValidationStrategy> validationStrategies;

    public ValidationContext() {
        this.validationStrategies = new LinkedHashSet<>();
    }

    public ValidationContext(Set<ValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    public Map<String, String> execute(BaseInput input) {
        ValidationStrategy passwordValidation;
        Map<String, String> errMsgMap = new LinkedHashMap<>();

        for (Iterator<ValidationStrategy> iterator = validationStrategies.iterator(); iterator.hasNext();) {
            passwordValidation = iterator.next();
            if (!passwordValidation.validate(input).get()) {
                errMsgMap.put(passwordValidation.getValidationType().name(),
                              passwordValidation.getDefaultErrorMsg());
            }
        }
        return errMsgMap;
    }

    public void addStrategy(ValidationStrategy condition) {
        validationStrategies.add(condition);
    }

    public void removeStrategy(ValidationStrategy condition) {
        validationStrategies.remove(condition);
    }
}
