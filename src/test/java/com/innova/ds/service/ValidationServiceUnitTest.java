package com.innova.ds.service;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.ValidationStrategy;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceUnitTest {

    @Mock
    ValidationStrategy lowercaseNumericOnlyRule;
    @Mock
    ValidationStrategy lengthRule;
    @Mock
    ValidationStrategy sequenceRule;
    @Mock
    ValidationService validationService;

    List<ValidationStrategy> passwordRules;

    @Before
    public void setUp() {
        passwordRules = Arrays.asList(lowercaseNumericOnlyRule, lengthRule, sequenceRule);
        validationService = new ValidationService(passwordRules);
    }

    public void assertResults(BaseInput input, Map<String, String> mapResultExpected) {

        setMockReturnObjectsByRuleType(mapResultExpected);
        // Act
        Map<String, String> mapResultMock = validationService.verifyPasswordStrategy(input);
        assertEquals(mapResultExpected, mapResultMock);
    }

    public void setMockReturnObjectsByRuleType(Map<String, String> mapResultExpected) {
        for (String ruleType : mapResultExpected.keySet()) {
            switch (ruleType) {
                case "LOWERCASE_NUMERIC_ONLY":
                    Mockito.when(lowercaseNumericOnlyRule.getRuleType()).thenReturn(RuleType.LOWERCASE_NUMERIC_ONLY.name());
                    Mockito.when(lowercaseNumericOnlyRule.getDefaultErrorMsg()).thenReturn(RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
                    break;
                case "LENGTH":
                    Mockito.when(lengthRule.getRuleType()).thenReturn(RuleType.LENGTH.name());
                    Mockito.when(lengthRule.getDefaultErrorMsg()).thenReturn(RuleType.LENGTH.getDescription());
                    break;
                case "SEQUENCE":
                    Mockito.when(sequenceRule.getRuleType()).thenReturn(RuleType.SEQUENCE.name());
                    Mockito.when(sequenceRule.getDefaultErrorMsg()).thenReturn(RuleType.SEQUENCE.getDescription());
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void testVerifyPasswordAllSuccessful() {
        BaseInput input = new BaseInput("1adsv1");
        Map<String, String> mapResultExpected = new HashMap<>();

        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithSequenceAndLengthAndLowercaseNumericOnlyFailure() {
        BaseInput input = new BaseInput("11111");
        Map<String, String> mapResultExpected = new HashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());

        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithLowercaseNumericOnlyAndSequenceFailure() {
        // Arrange
        BaseInput input = new BaseInput("143143%#$@1");
        Map<String, String> mapResultExpected = new HashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithLowercaseNumericOnlyAndLengthFailure() {
        // Arrange
        BaseInput input = new BaseInput("1@1");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithSequenceAndLengthFailure() {
        // Arrange
        BaseInput input = new BaseInput("pm143143dsbdrfh");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithSequenceFailure() {
        // Arrange
        BaseInput input = new BaseInput("1212dg");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithLengthFailure() {
        // Arrange
        BaseInput input = new BaseInput("62stjqjlswka0");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @Test
    public void testVerifyPasswordWithLowercaseNumericOnlyFailure() {
        // Arrange
        BaseInput input = new BaseInput("123456");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

}
