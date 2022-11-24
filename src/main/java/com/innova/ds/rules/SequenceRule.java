package com.innova.ds.rules;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.utils.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sequence_rule")
public class SequenceRule implements ValidationStrategy {

    public final String REGEXP_CONSECUTIVE = "(.+)\\1";

    @Override
    public <T extends BaseInput> Boolean validate(T input) {
        return StringUtils.executeRegex(REGEXP_CONSECUTIVE, input.getPassword()) == 0;
    }

    @Override
    public String getDefaultErrorMsg() {
        return RuleType.SEQUENCE.getDescription();
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.SEQUENCE;
    }

    public String getREGEXP_CONSECUTIVE() {
        return REGEXP_CONSECUTIVE;
    }
}
