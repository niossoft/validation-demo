package com.innova.ds.service.strategy;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.LengthRule;
import com.innova.ds.rules.LowercaseNumericOnlyRule;
import com.innova.ds.rules.SequenceRule;
import com.innova.ds.rules.ValidationStrategy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

public class ValidationServiceStrategyUnitTest {

    static SequenceRule sequenceRule = new SequenceRule();
    static LengthRule lengthRule = new LengthRule();
    static LowercaseNumericOnlyRule lowercaseNumericOnlyRule = new LowercaseNumericOnlyRule();
    static List<ValidationStrategy> allRules = Arrays.asList(lengthRule, sequenceRule, lowercaseNumericOnlyRule);

    @ParameterizedTest
    @MethodSource("testParameters")
    public void testUseCaseCollectionsWithValidationTestConfig(final ValidationTestStrategy vtc) {
        vtc.execute();
    }

    public static List<ValidationTestStrategy> testParameters() {
        List<ValidationTestStrategy> allTestConfig = new ArrayList<>();

        // Successful Password Config
        ValidationRulesTestConfig testAllRulesWithSuccessful = new ValidationRulesTestConfig(allRules, new ArrayList<>());

        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(Arrays.asList("12fb1", "gfdnfd158dvg"), testAllRulesWithSuccessful));

        // Failure Password Config
        ValidationRulesTestConfig testAllRulesWithAllError = new ValidationRulesTestConfig(allRules, Arrays.asList(RuleType.LOWERCASE_NUMERIC_ONLY, RuleType.SEQUENCE, RuleType.LENGTH));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(Arrays.asList("11", "gfdnfdslbjnfdljbndflbbdfnfdl"), testAllRulesWithAllError));

        ValidationRulesTestConfig testAllRulesWithLengthRuleError = new ValidationRulesTestConfig(allRules, Arrays.asList(RuleType.LENGTH));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(Arrays.asList("12f4", "ab8d", "g8d6", "62stjqjlswka0", "jkwoxa4zyj8gr", "b8ziwa96u1089"), testAllRulesWithLengthRuleError));

        ValidationRulesTestConfig testAllRulesWithSequenceRuleError = new ValidationRulesTestConfig(allRules, Arrays.asList(RuleType.SEQUENCE));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(Arrays.asList("1212dg", "abab8de", "g8dddsv", "62stjjls", "jkwoxx4z", "b8z6uu08"), testAllRulesWithSequenceRuleError));

        ValidationRulesTestConfig testAllRulesWithLowercaseNumericOnlyRuleError = new ValidationRulesTestConfig(allRules, Arrays.asList(RuleType.LOWERCASE_NUMERIC_ONLY));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(Arrays.asList("123456", "abcdef", "#pm143", "ehn42?"), testAllRulesWithLowercaseNumericOnlyRuleError));

        return allTestConfig;
    }

    private static List<ValidationRulesTestConfig> getValidationTestConfigsWithMultipleBaseInput(List<String> passwordList, ValidationRulesTestConfig validationTestConfig) {
        List<ValidationRulesTestConfig> list = new ArrayList<>();
        for (String password : passwordList) {
            validationTestConfig.input = new BaseInput(password);
            list.add(validationTestConfig);
        }
        return list;
    }

}
