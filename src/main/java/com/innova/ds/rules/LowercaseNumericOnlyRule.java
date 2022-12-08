package com.innova.ds.rules;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lowercase_numeric_only_rule")
public class LowercaseNumericOnlyRule implements ValidationStrategy {

    private static final int MIN_NUMERIC_NUM = 1;
    private static final int MIN_LOWERCASE_NUM = 1;

    @Override
    public <T extends BaseInput> Boolean validate(T input) {
        int numericCnt = 0;
        int lowerCnt = 0;
        int cnt = 0;

        for(char node : input.getPassword().toCharArray()) {
            if(!Character.isDigit(node) && !Character.isLowerCase(node)) {
                cnt ++;
            }
            if(Character.isLowerCase(node)) {
                lowerCnt ++;
            }
            if(Character.isDigit(node)) {
                numericCnt ++;
            }
        }
        return lowerCnt >= MIN_LOWERCASE_NUM
                && numericCnt >= MIN_NUMERIC_NUM
                && cnt == 0;
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
