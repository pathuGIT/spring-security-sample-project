package com.athome.springTest.controller;

import com.athome.springTest.model.Enrollments;
import com.athome.springTest.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('SUB_ADMIN') or hasAuthority('USER')")
    @PostMapping
    public Enrollments enroll(@RequestParam int academicCourse_id){
        return enrollService.enroll(academicCourse_id);
    }


}
