package com.athome.springTest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class SuperAdminController {

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/hello")
    public String greet(){
        return "Hello LMS";
    }
}
