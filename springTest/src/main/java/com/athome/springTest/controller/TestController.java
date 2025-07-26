package com.athome.springTest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String publicTest() {
        return "Public access okay!!";
    }

    @GetMapping("/private")
    public String privateTest() {
        return "Only Private access okay!!";
    }
}
