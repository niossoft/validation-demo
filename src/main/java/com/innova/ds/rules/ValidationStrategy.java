package com.innova.ds.rules;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;

public interface ValidationStrategy {

    <T extends BaseInput> Boolean validate(T input);
    String getDefaultErrorMsg();
    RuleType getRuleType();

}
