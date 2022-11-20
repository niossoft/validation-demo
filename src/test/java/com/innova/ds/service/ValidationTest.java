package com.innova.ds.service;

import com.innova.ds.constant.ValidationType;
import com.innova.ds.dto.BaseInput;
import com.innova.ds.validation.PasswordValidationStrategy;
import com.innova.ds.validation.ValidationStrategy;
import com.innova.ds.validation.context.ValidationContext;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class ValidationTest {

    /**
     * https://github.com/RathaKM/JavaAdhocExamples/blob/develop/src/test/java/com/validator/enumuse/ValidatorTest.java
     * */
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
    public void testLowercaseNumericOnlyValidation() {
        input = new BaseInput("A1a1Bsgf");
        Object[] arrResultActual = vc.execute(input).keySet().toArray();
        Object[] arrResultExpected = {ValidationType.LOWERCASE_NUMERIC_ONLY.name()};
        Assert.assertArrayEquals(arrResultExpected, arrResultActual);
    }

    @Test
    public void testLengthRangeValidation() {
        input = new BaseInput("1a1");
        Object[] arrResultActual = vc.execute(input).keySet().toArray();
        Object[] arrResultExpected = {ValidationType.LENGTH_RANGE.name()};
        Assert.assertArrayEquals(arrResultExpected, arrResultActual);
    }

    @Test
    public void testMinLowercaseValidation() {
        input = new BaseInput("123456");
        Object[] arrResultActual = vc.execute(input).keySet().toArray();
        Object[] arrResultExpected = {ValidationType.MIN_LOWERCASE.name()};
        Assert.assertArrayEquals(arrResultExpected, arrResultActual);
    }

    @Test
    public void testMinNumericValidation() {
        input = new BaseInput("qfdsgbs");
        Object[] arrResultActual = vc.execute(input).keySet().toArray();
        Object[] arrResultExpected = {ValidationType.MIN_NUMERIC.name()};
        Assert.assertArrayEquals(arrResultExpected, arrResultActual);
    }

    @Test
    public void testNoSequenceValidation() {
        input = new BaseInput("aa8754436");
        Object[] arrResultActual = vc.execute(input).keySet().toArray();
        Object[] arrResultExpected = {ValidationType.NO_SEQUENCE.name()};
        Assert.assertArrayEquals(arrResultExpected, arrResultActual);
    }
}
