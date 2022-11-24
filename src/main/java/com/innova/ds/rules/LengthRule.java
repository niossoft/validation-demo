package com.innova.ds.rules;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("length_rule")
public class LengthRule implements ValidationStrategy {

    private final int MIN_LENGTH = 5;
    private final int MAX_LENGTH = 12;

    @Override
    public <T extends BaseInput> Boolean validate(T input) {
        String password = input.getPassword();
        return password.length() <= MAX_LENGTH
                && password.length() >= MIN_LENGTH;
    }

    @Override
    public String getDefaultErrorMsg() {
        return RuleType.LENGTH.getDescription();
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.LENGTH;
    }

}
