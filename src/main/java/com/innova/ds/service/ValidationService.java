package com.innova.ds.service;

import com.innova.ds.dto.BaseInput;
import com.innova.ds.validation.*;
import com.innova.ds.validation.context.ValidationContext;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ValidationService {

    public Map<String, String> verifyPasswordStrategy(BaseInput baseInput) {
        Set<ValidationStrategy> strategies = new LinkedHashSet<>();
        strategies.add(PasswordValidationStrategy.LOWERCASE_NUMERIC_ONLY);
        strategies.add(PasswordValidationStrategy.LENGTH_RANGE);
        strategies.add(PasswordValidationStrategy.MIN_LOWERCASE);
        strategies.add(PasswordValidationStrategy.MIN_NUMERIC);
        strategies.add(PasswordValidationStrategy.NO_SEQUENCE);

        return new ValidationContext(strategies).execute(baseInput);
    }
}
