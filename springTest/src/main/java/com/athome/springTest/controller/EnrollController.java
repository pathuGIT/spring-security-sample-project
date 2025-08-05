package com.athome.springTest.controller;

import com.athome.springTest.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;



}
