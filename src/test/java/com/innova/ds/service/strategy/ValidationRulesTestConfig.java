package com.innova.ds.service.strategy;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.ValidationStrategy;
import com.innova.ds.service.ValidationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationRulesTestConfig implements ValidationTestStrategy {

    BaseInput input;
    List<ValidationStrategy> rules;
    List<RuleType> errRuleTypesExpected;
    List<String> inputStringList;

    public ValidationRulesTestConfig() {

    }
    public ValidationRulesTestConfig(List<ValidationStrategy> rules,
                                     List<RuleType> errRuleTypesExpected) {
        this.rules = rules;
        this.errRuleTypesExpected = errRuleTypesExpected;
    }

    public void execute() {
        // Step 1: set Rules for Validation
        ValidationService validationService = new ValidationService(getRules());
        // Step 2: get Actual Result From ValidationService
        Map<String, String> map = validationService.verifyPasswordStrategy(getBaseInput());
        // Step 3: assert Result with Expected
        assertResult(map, convertRuleTypeToErrMsgMap(getErrRuleTypesExpected()));
    }

    @Override
    public void assertResult(Map<String, String> errMsgMapActual, Map<String, String> errMsgMapExpected) {
        assertEquals(errMsgMapActual, errMsgMapExpected);
    }

    @Override
    public void configureRules(List<ValidationStrategy> rules) {
        this.rules = rules;
    }

    @Override
    public void configureErrRuleTypesExpected(List<RuleType> errRuleTypesExpected) {
        this.errRuleTypesExpected = errRuleTypesExpected;
    }

    public void configureTestInputStringList(List<String> inputStringList) {
        this.inputStringList = inputStringList;
    }

    @Override
    public BaseInput getBaseInput() {
        return input;
    }

    @Override
    public List<ValidationStrategy> getRules() {
        return rules;
    }

    @Override
    public List<RuleType> getErrRuleTypesExpected() {
        return errRuleTypesExpected;
    }



    private Map<String, String> convertRuleTypeToErrMsgMap(List<RuleType> ruleTypes) {
        Map<String, String> errMsgMap = new HashMap<>();
        for (RuleType ruleType : ruleTypes) {
            errMsgMap.put(ruleType.name(), ruleType.getDescription());
        }
        return errMsgMap;
    }

}
