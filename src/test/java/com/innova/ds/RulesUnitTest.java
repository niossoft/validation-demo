package com.innova.ds;

import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.LengthRule;
import com.innova.ds.rules.LowercaseNumericOnlyRule;
import com.innova.ds.rules.SequenceRule;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RulesUnitTest {

    @ParameterizedTest
    @ValueSource(strings = { "1dsvds", "a816561" })
    public void testVerifyPasswordWithLowercaseNumericOnlySuccessful(String password) {
        BaseInput input = new BaseInput(password);
        LowercaseNumericOnlyRule rule = new LowercaseNumericOnlyRule();
        Assert.assertTrue(rule.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "123456", "abcdef", "15gdnj#", "5fdbfd測試", "Aa5nb9fd" })
    public void testVerifyPasswordWithLowercaseNumericOnlyFailure(String password) {
        BaseInput input = new BaseInput(password);
        LowercaseNumericOnlyRule rule = new LowercaseNumericOnlyRule();
        Assert.assertFalse(rule.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abcde", "12345", "abcdefghijkl", "123456789012" })
    public void testVerifyPasswordWithLengthBoundarySuccessful(String password) {
        BaseInput input = new BaseInput(password);
        LengthRule rule = new LengthRule();
        Assert.assertTrue(rule.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abcdefghijklm", "1234567890123" })
    public void testVerifyPasswordWithLengthLongerFailure(String password) {
        BaseInput input = new BaseInput(password);
        LengthRule rule = new LengthRule();
        Assert.assertFalse(rule.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abcd", "1234", "3rv", "123" })
    public void testVerifyPasswordWithLengthShorterFailure(String password) {
        BaseInput input = new BaseInput(password);
        LengthRule rule = new LengthRule();
        Assert.assertFalse(rule.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abc1abc", "1312131", "abc2abc1513", "gjk153h153fd" })
    public void testVerifyPasswordWithSequenceSuccessful(String password) {
        BaseInput input = new BaseInput(password);
        SequenceRule rule = new SequenceRule();
        Assert.assertTrue(rule.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abcabc", "1111", "abcabc1513", "gjk153153fd" })
    public void testVerifyPasswordWithSequenceFailure(String password) {
        BaseInput input = new BaseInput(password);
        SequenceRule rule = new SequenceRule();
        Assert.assertFalse(rule.validate(input));
    }

}
