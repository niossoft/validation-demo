package com.innova.ds.rules;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Qualifier("lowercase_numeric_only_rule")
public class LowercaseNumericOnlyRule implements ValidationStrategy {

    private static final int MIN_NUMERIC_NUM = 1;
    private static final int MIN_LOWERCASE_NUM = 1;

    @Override
    public <T extends BaseInput> Boolean validate(T input) {
        AtomicInteger numericCnt = new AtomicInteger();
        AtomicInteger lowerCnt = new AtomicInteger();
        AtomicInteger cnt = new AtomicInteger();

        for(char node : input.getPassword().toCharArray()) {
            if(!Character.isDigit(node) && !Character.isLowerCase(node)) {
                cnt.getAndIncrement();
            }
            if(Character.isLowerCase(node)) {
                lowerCnt.getAndIncrement();
            }
            if(Character.isDigit(node)) {
                numericCnt.getAndIncrement();
            }
        }
        return lowerCnt.intValue() >= MIN_LOWERCASE_NUM
                && numericCnt.intValue() >= MIN_NUMERIC_NUM
                && cnt.intValue() == 0;
    }

    @Override
    public String getDefaultErrorMsg() {
        return RuleType.LOWERCASE_NUMERIC_ONLY.getDescription();
    }

    @Override
    public String getRuleType() {
        return RuleType.LOWERCASE_NUMERIC_ONLY.name();
    }

}
