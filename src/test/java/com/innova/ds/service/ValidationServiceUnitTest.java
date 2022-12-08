package com.innova.ds.service;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.LowercaseNumericOnlyRule;
import com.innova.ds.rules.ValidationStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.runner.RunWith;
import org.mockito.*;

import static org.mockito.BDDMockito.given;


import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceUnitTest {

    @Mock
    ValidationStrategy lowercaseNumericOnlyRule;
    @Mock
    ValidationStrategy lengthRule;
    @Mock
    ValidationStrategy sequenceRule;

    List<ValidationStrategy> passwordRules;
    @InjectMocks
    ValidationService validationService;
    @Spy
    Map<String, String> mapResults;

    @Before
    public void setUp() {
        passwordRules = Arrays.asList(lowercaseNumericOnlyRule, lengthRule, sequenceRule);
        validationService = new ValidationService(passwordRules);
    }

    @Test
//    @ParameterizedTest
//    @ValueSource(strings = { "11@", "aa!", "pm143%#$@dsjjghjkmcndnh", "ehehn424@測試字串rbsvecsvbsdfb" })
    public void test() {

//        validationService = new ValidationService(passwordRules);
//        Mockito.when(lowercaseNumericOnlyRule.getRuleType())
        Mockito.when(lowercaseNumericOnlyRule.getRuleType()).thenReturn(RuleType.LOWERCASE_NUMERIC_ONLY.name());
        Mockito.when(lowercaseNumericOnlyRule.getDefaultErrorMsg()).thenReturn(RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());

        BaseInput input = new BaseInput();
        Map<String, String> mapResultExpected = new HashMap<>();


        mapResults.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
//        mapResults.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
//        mapResults.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());

        Map<String, String> mapResultMock = validationService.verifyPasswordStrategy(input);


        System.out.println(mapResultMock);

//        Mockito.doReturn(mapResultExpected).when(validationService.verifyPasswordStrategy(input)).get(RuleType.LOWERCASE_NUMERIC_ONLY.name());

//        assertResults(input, mapResultExpected);

//        Map<String, String> mapResult = validationService.verifyPasswordStrategy(input);
    }

//    public void assertResults(BaseInput input, Map<String, String> mapResultExpected) {
//        // Act
//        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
//        assertEquals(mapResultExpected, mapResultActual);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "11@", "aa!", "pm143%#$@dsjjghjkmcndnh", "ehehn424@測試字串rbsvecsvbsdfb" })
//    public void testVerifyPasswordWithSequenceAndLengthAndLowercaseNumericOnlyFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new HashMap<>();
//        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
//        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
//        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "1@1@ewg", "a@a@svs", "143143%#$@1", "測試字串rbrbcsv" })
//    public void testVerifyPasswordWithLowercaseNumericOnlyAndSequenceFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new LinkedHashMap<>();
//        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
//        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "1@1", "a@a#", "pm143%#$@dbfdb", "ehn424@測試字串rbsvecsvbsd" })
//    public void testVerifyPasswordWithLowercaseNumericOnlyAndLengthFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new LinkedHashMap<>();
//        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
//        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "a111", "1aaa", "pm143143dsbdrfh", "ehn4242bvsgmdsad", "1jkjkvs529gcdbdf16" })
//    public void testVerifyPasswordWithSequenceAndLengthFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new LinkedHashMap<>();
//        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
//        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "1212dg", "abab8de", "g8dddsv", "62stjjls", "jkwoxx4z", "b8z6uu08" })
//    public void testVerifyPasswordWithSequenceFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new LinkedHashMap<>();
//        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "12f4", "ab8d", "g8d6", "62stjqjlswka0", "jkwoxa4zyj8grsgs", "b8ziwa96u1089fdbfd" })
//    public void testVerifyPasswordWithLengthFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new LinkedHashMap<>();
//        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "123456", "abcdef", "#pm143", "ehn42?", "123測試字串abc" })
//    public void testVerifyPasswordWithLowercaseNumericOnlyFailure(String password) {
//        // Arrange
//        BaseInput input = new BaseInput(password);
//        Map<String, String> mapResultExpected = new LinkedHashMap<>();
//        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
//        // Assert
//        assertResults(input, mapResultExpected);
//    }

}
