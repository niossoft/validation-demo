package com.innova.ds.rules.config;

import com.innova.ds.rules.LengthRule;
import com.innova.ds.rules.LowercaseNumericOnlyRule;
import com.innova.ds.rules.SequenceRule;
import com.innova.ds.rules.ValidationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RulesConfiguration {

//    @Bean("passwordRules")
    public List<ValidationStrategy> paaswordRuleList(LowercaseNumericOnlyRule lowercaseNumericOnlyRule,
                                                     LengthRule lengthRule,
                                                     SequenceRule sequenceRule) {
        return Arrays.asList(lowercaseNumericOnlyRule, lengthRule, sequenceRule);
    }

}
