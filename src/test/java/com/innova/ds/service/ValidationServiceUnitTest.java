package com.innova.ds.service;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.ValidationStrategy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ValidationServiceUnitTest {

    @Mock
    private ValidationStrategy lowercaseNumericOnlyRule;
    @Mock
    private ValidationStrategy lengthRule;
    @Mock
    private ValidationStrategy sequenceRule;
    @MockBean
    private List<ValidationStrategy> passwordRules;
    @Autowired
    private ValidationService validationService;

    @ParameterizedTest
    @ValueSource(strings = { "11@", "aa!", "pm143%#$@dsjjghjkmcndnh", "ehehn424@測試字串rbsvecsvbsdfb" })
    public void testVerifyPasswordWithSequenceAndLengthAndLowercaseNumericOnlyFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1@1", "a@a1", "pm143%#$@dsjghjkm", "ehn424@測試字串rbsvecsvbsd" })
    public void testVerifyPasswordWithSequenceAndLengthFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1@1", "a@a#", "pm143%#$@dbfdb", "ehn424@測試字串rbsvecsvbsd" })
    public void testVerifyPasswordWithLowercaseNumericOnlyAndLengthFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "11111", "aaaaa", "pm143143dsb@", "ehn4242bvs!" })
    public void testVerifyPasswordWithLowercaseNumericOnlyAndSequenceFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1212dg", "abab8de", "g8dddsv", "62stjjls", "jkwoxx4z", "b8z6uu08" })
    public void testVerifyPasswordWithSequenceFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "12f4", "ab8d", "g8d6", "62stjqjlswka0", "jkwoxa4zyj8gr", "b8ziwa96u1089" })
    public void testVerifyPasswordWithLengthFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "123456", "abcdef", "#pm143", "ehn42?" })
    public void testVerifyPasswordWithLowercaseNumericOnlyFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        // Assert
        assertEquals(mapResultExpected, mapResultActual);
    }

}
