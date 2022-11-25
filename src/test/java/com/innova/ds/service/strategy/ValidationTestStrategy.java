package com.innova.ds.service.strategy;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.ValidationStrategy;

import java.util.List;
import java.util.Map;

public interface ValidationTestStrategy {

    void execute();

    void assertResult(final Map<String, String> errMsgMapActual,
                      final Map<String, String> errMsgMapExpected);

    void configureRules(List<ValidationStrategy> rules);

    void configureErrRuleTypesExpected(List<RuleType> errRuleTypesExpected);

    BaseInput getBaseInput();

    List<ValidationStrategy> getRules();

    List<RuleType> getErrRuleTypesExpected();

}
