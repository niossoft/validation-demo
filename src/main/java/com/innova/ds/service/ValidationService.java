package com.innova.ds.service;

import com.innova.ds.dto.BaseInput;
import com.innova.ds.rules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidationService {

    @Autowired
//    @Qualifier("passwordRules")
    private List<ValidationStrategy> passwordRules;

    public ValidationService() {

    }

    public ValidationService(List<ValidationStrategy> passwordRules) {
        this.passwordRules = passwordRules;
    }

    public Map<String, String> verifyPasswordStrategy(BaseInput baseInput) {
        Map<String, String> errMsgMap = new HashMap<>();
        for(ValidationStrategy passwordValidation : passwordRules) {
            if (!passwordValidation.validate(baseInput)) {
                errMsgMap.put(passwordValidation.getRuleType(),
                              passwordValidation.getDefaultErrorMsg());
            }
        }
        return errMsgMap;
    }

}
