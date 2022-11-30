package com.innova.ds.service.strategy;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.LengthRule;
import com.innova.ds.rules.LowercaseNumericOnlyRule;
import com.innova.ds.rules.SequenceRule;
import com.innova.ds.rules.ValidationStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

public class ValidationServiceStrategyUnitTest {

    static SequenceRule sequenceRule;
    static LengthRule lengthRule;
    static LowercaseNumericOnlyRule lowercaseNumericOnlyRule;
    static List<ValidationStrategy> allRules;

    @BeforeAll
    public static void setUp() {
        sequenceRule = new SequenceRule();
        lengthRule = new LengthRule();
        lowercaseNumericOnlyRule = new LowercaseNumericOnlyRule();
        allRules = Arrays.asList(lengthRule, sequenceRule, lowercaseNumericOnlyRule);
    }

    @ParameterizedTest
    @MethodSource("testParameters")
    public void testUseCaseCollectionsWithValidationTestConfig(final ValidationTestStrategy vtc) {
        vtc.execute();
    }

    public static List<ValidationTestStrategy> testParameters() {
        List<ValidationTestStrategy> allTestConfig = new ArrayList<>();

        // Successful Password Config
        ValidationRulesTestConfig testAllRulesWithSuccessful = new ValidationRulesTestConfig();
        testAllRulesWithSuccessful.configureRules(allRules);
        testAllRulesWithSuccessful.configureErrRuleTypesExpected(new ArrayList<>());
        testAllRulesWithSuccessful.configureTestInputStringList(Arrays.asList("12fb1", "cs45c13s", "gfdnfd158dvg", "a15fsve1ve"));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(testAllRulesWithSuccessful));

        // Failure Password Config
        ValidationRulesTestConfig testAllRulesWithAllError = new ValidationRulesTestConfig();
        testAllRulesWithAllError.configureRules(allRules);
        testAllRulesWithAllError.configureErrRuleTypesExpected(Arrays.asList(RuleType.LOWERCASE_NUMERIC_ONLY, RuleType.SEQUENCE, RuleType.LENGTH));
        testAllRulesWithAllError.configureTestInputStringList(Arrays.asList("11", "gfdnfdslbjnfdljbndflbbdfnfdl", "@@a1", "測試字串1123456#456aaa"));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(testAllRulesWithAllError));

        ValidationRulesTestConfig testAllRulesWithLengthRuleError = new ValidationRulesTestConfig(allRules, Arrays.asList(RuleType.LENGTH));
        testAllRulesWithLengthRuleError.configureRules(allRules);
        testAllRulesWithLengthRuleError.configureErrRuleTypesExpected(Arrays.asList(RuleType.LENGTH));
        testAllRulesWithLengthRuleError.configureTestInputStringList(Arrays.asList("12f4", "ab8d", "g8d6", "62stjqjlswka0", "jkwoxa4zyj8gr", "b8ziwa96u1089"));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(testAllRulesWithLengthRuleError));

        ValidationRulesTestConfig testAllRulesWithSequenceRuleError = new ValidationRulesTestConfig();
        testAllRulesWithSequenceRuleError.configureRules(allRules);
        testAllRulesWithSequenceRuleError.configureErrRuleTypesExpected(Arrays.asList(RuleType.SEQUENCE));
        testAllRulesWithSequenceRuleError.configureTestInputStringList(Arrays.asList("1212dg", "abab8de", "g8dddsv", "62stjjls", "jkwoxx4z", "b8z6uu08"));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(testAllRulesWithSequenceRuleError));

        ValidationRulesTestConfig testAllRulesWithLowercaseNumericOnlyRuleError = new ValidationRulesTestConfig();
        testAllRulesWithLowercaseNumericOnlyRuleError.configureRules(allRules);
        testAllRulesWithLowercaseNumericOnlyRuleError.configureErrRuleTypesExpected(Arrays.asList(RuleType.LOWERCASE_NUMERIC_ONLY));
        testAllRulesWithLowercaseNumericOnlyRuleError.configureTestInputStringList(Arrays.asList("123456", "abcdef", "#pm143", "ehn42?", "我gfsgv1531"));
        allTestConfig.addAll(getValidationTestConfigsWithMultipleBaseInput(testAllRulesWithLowercaseNumericOnlyRuleError));

        return allTestConfig;
    }

    private static List<ValidationRulesTestConfig> getValidationTestConfigsWithMultipleBaseInput(ValidationRulesTestConfig validationTestConfig) {
        List<ValidationRulesTestConfig> list = new ArrayList<>();
        for (String password : validationTestConfig.inputStringList) {
            ValidationRulesTestConfig config = new ValidationRulesTestConfig();
            config.configureRules(validationTestConfig.rules);
            config.configureErrRuleTypesExpected(validationTestConfig.errRuleTypesExpected);
            config.input = new BaseInput(password);
            list.add(config);
        }
        return list;
    }

}
