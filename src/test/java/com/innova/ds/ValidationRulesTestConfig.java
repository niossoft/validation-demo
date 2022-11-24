package com.innova.ds;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.ValidationStrategy;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationRulesTestConfig implements ValidationTestStrategy {

    BaseInput input;
    List<ValidationStrategy> rules;
    List<RuleType> errRuleTypesExpected;

    public ValidationRulesTestConfig() {

    }
    public ValidationRulesTestConfig(List<ValidationStrategy> rules,
                                     List<RuleType> errRuleTypesExpected) {
        this.rules = rules;
        this.errRuleTypesExpected = errRuleTypesExpected;
    }

    @Override
    public void assertResult(Map<String, String> errMsgMapActual, Map<String, String> errMsgMapExpected) {
        assertEquals(errMsgMapActual, errMsgMapExpected);
    }

}
