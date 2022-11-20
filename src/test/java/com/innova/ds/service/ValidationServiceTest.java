package com.innova.ds.service;

import com.innova.ds.constant.ErrorMsgType;
import com.innova.ds.constant.ValidationType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.validation.PasswordValidationStrategy;
import com.innova.ds.validation.ValidationStrategy;
import com.innova.ds.validation.context.ValidationContext;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ValidationServiceTest {

    @Autowired
    ValidationService validationService;
    @Mock
    ValidationService mockValidationService;

    static BaseInput input;
    static ValidationContext vc;
    static Set<ValidationStrategy> strategies = new LinkedHashSet<>();

    @BeforeAll
    static void beforeAll() {
        strategies.add(PasswordValidationStrategy.LOWERCASE_NUMERIC_ONLY);
        strategies.add(PasswordValidationStrategy.LENGTH_RANGE);
        strategies.add(PasswordValidationStrategy.MIN_LOWERCASE);
        strategies.add(PasswordValidationStrategy.MIN_NUMERIC);
        strategies.add(PasswordValidationStrategy.NO_SEQUENCE);
        vc = new ValidationContext(strategies);
    }

    @Test
    public void testVerifyPasswordWithLowercaseNumericOnlyError() {
        input = new BaseInput("123#456a");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(ValidationType.LOWERCASE_NUMERIC_ONLY.name(),
                              ErrorMsgType.LOWERCASE_NUMERIC_ONLY.getDescription());
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        Mockito.when(mockValidationService.verifyPasswordStrategy(input)).thenReturn(mapResultExpected);

        assertEquals(mapResultExpected, mapResultActual);
    }
    @Test
    public void testVerifyPasswordWithLengthRangeError() {
        input = new BaseInput("123456asfyhdfbfdb");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(ValidationType.LENGTH_RANGE.name(),
                              ErrorMsgType.LENGTH_RANGE.getDescription());
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        Mockito.when(mockValidationService.verifyPasswordStrategy(input)).thenReturn(mapResultExpected);

        assertEquals(mapResultExpected, mapResultActual);
    }

    @Test
    public void testVerifyPasswordWithMinLowercaseError() {
        input = new BaseInput("123456");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(ValidationType.MIN_LOWERCASE.name(),
                              ErrorMsgType.MIN_LOWERCASE.getDescription());
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        Mockito.when(mockValidationService.verifyPasswordStrategy(input)).thenReturn(mapResultExpected);

        assertEquals(mapResultExpected, mapResultActual);
    }
    @Test
    public void testVerifyPasswordWithMinNumericError() {
        input = new BaseInput("afgshjh");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(ValidationType.MIN_NUMERIC.name(),
                              ErrorMsgType.MIN_NUMERIC.getDescription());
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        Mockito.when(mockValidationService.verifyPasswordStrategy(input)).thenReturn(mapResultExpected);

        assertEquals(mapResultExpected, mapResultActual);
    }

    @Test
    public void testVerifyPasswordWithNoSequenceError() {
        input = new BaseInput("aafg32shjh");
        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(ValidationType.NO_SEQUENCE.name(),
                              ErrorMsgType.NO_SEQUENCE.getDescription());
        Map<String, String> mapResultActual = validationService.verifyPasswordStrategy(input);
        Mockito.when(mockValidationService.verifyPasswordStrategy(input)).thenReturn(mapResultExpected);

        assertEquals(mapResultExpected, mapResultActual);
    }
}