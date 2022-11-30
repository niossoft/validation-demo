package com.innova.ds.service;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.ValidationStrategy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.Mock;
import org.mockito.Mockito;
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

    public void assertResults(BaseInput input, Map<String, String> mapResultExpected) {
        // Act
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "11@", "aa!", "pm143%#$@dsjjghjkmcndnh", "ehehn424@測試字串rbsvecsvbsdfb" })
    public void testVerifyPasswordWithSequenceAndLengthAndLowercaseNumericOnlyFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1@1@ewg", "a@a@svs", "143143%#$@1", "測試字串rbrbcsv" })
    public void testVerifyPasswordWithLowercaseNumericOnlyAndSequenceFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1@1", "a@a#", "pm143%#$@dbfdb", "ehn424@測試字串rbsvecsvbsd" })
    public void testVerifyPasswordWithLowercaseNumericOnlyAndLengthFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "a111", "1aaa", "pm143143dsbdrfh", "ehn4242bvsgmdsad", "1jkjkvs529gcdbdf16" })
    public void testVerifyPasswordWithSequenceAndLengthFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1212dg", "abab8de", "g8dddsv", "62stjjls", "jkwoxx4z", "b8z6uu08" })
    public void testVerifyPasswordWithSequenceFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "12f4", "ab8d", "g8d6", "62stjqjlswka0", "jkwoxa4zyj8grsgs", "b8ziwa96u1089fdbfd" })
    public void testVerifyPasswordWithLengthFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "123456", "abcdef", "#pm143", "ehn42?", "123測試字串abc" })
    public void testVerifyPasswordWithLowercaseNumericOnlyFailure(String password) {
        // Arrange
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());
        // Assert
        assertResults(input, mapResultExpected);
    }

}
