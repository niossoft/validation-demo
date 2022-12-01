package com.innova.ds.service;

import com.innova.ds.constant.RuleType;
import com.innova.ds.dto.BaseInput;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration
public class ValidationServiceIntegrationTest {

    @Autowired
    ValidationService validationService;
    @Mock
    ValidationService mockValidationService;

    public void assertResults(BaseInput input, Map<String, String> mapResultExpected) {
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        Mockito.when(mockValidationService.verifyPasswordStrategy(input)).thenReturn(mapResultExpected);
        assertEquals(mapResultExpected, mapResultActual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1dsvds", "a816561", "e0wk7o76", "lo06cvxez", "txhvg7qvhx5x", "b3ulp49mgqc6",
                             "dg156sd", "5a2k957lvk", "c9bsz" })
    public void testVerifyPasswordWithSuccessful(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();

        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "99@", "zzz!", "pmpm143%#$@dgdgsvdsgfn", "fewvwvhn424#@測試字串rb" })
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
    @ValueSource(strings = { "99@ewg", "zdzdv#svs#", "543543%#$@1", "測試字串175rbrb" })
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
    @ValueSource(strings = { "1@1v", "azf#", "pm14f43%#$@dbfdb", "ehnfhn424@測試字串15bvsd" })
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
    @ValueSource(strings = { "a888", "1ghh", "pm11789dsbfd6", "bsvsvs1386gmdsad", "1jvjv5kdsvds516" })
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
    @ValueSource(strings = { "123456", "abcdef", "0987654321", "zyxwvsrt", "#12345" })
    public void testVerifyPasswordWithLowercaseNumericOnlyFailure(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());

        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "123afg1sdv16sac", "123456asfyhdfbfdb", "grgnhkmuy12ko", "98k1536vdsgvds" })
    public void testVerifyPasswordWithLengthLongerFailure(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());

        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "12a4", "8hg6", "1a5", "r53", "a1", "8g" })
    public void testVerifyPasswordWithLengthShorterFailure(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LENGTH.name(), RuleType.LENGTH.getDescription());

        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "abrab", "gsdesd", "dsfdsedrb", "vdgds", "dsbdsg" })
    public void testVerifyPasswordWithMinNumericFailure(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());

        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "15131516545", "2542572", "54245", "123658", "527483542586" })
    public void testVerifyPasswordWithMinLowercaseFailure(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.LOWERCASE_NUMERIC_ONLY.name(), RuleType.LOWERCASE_NUMERIC_ONLY.getDescription());

        assertResults(input, mapResultExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "abcabc1", "123123sdv", "1212g", "11sacva", "a1a1g" })
    public void testVerifyPasswordWithNoSequenceFailure(String password) {
        BaseInput input = new BaseInput(password);
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(RuleType.SEQUENCE.name(), RuleType.SEQUENCE.getDescription());

        assertResults(input, mapResultExpected);
    }

}