package com.innova.ds.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test/")
public class TestController {


    @GetMapping("/hello")
    public ResponseEntity<String> getHelloMsg() {
        return new ResponseEntity("Hello from Kevin!", HttpStatus.OK);
    }
}
