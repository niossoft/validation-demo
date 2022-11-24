package com.innova.ds.controller;

import com.innova.ds.dto.BaseInput;
import com.innova.ds.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping("/verify/password")
    public ResponseEntity<String> verifyPassword(@RequestBody BaseInput baseDto) {
        Map<String, String> errMsgMap = validationService.verifyPasswordStrategy(baseDto);
        return new ResponseEntity(errMsgMap, HttpStatus.OK);
    }
}
