package com.athome.springTest.controller;

import com.athome.springTest.model.AcademicCourse;
import com.athome.springTest.service.AcademicCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academic_course")
public class AcademicCourseController {
    @Autowired
    private AcademicCourseService academicCourseService;

    @PostMapping
    public ResponseEntity<?> addAcademicCourse(@RequestParam int course_id, @RequestParam int academic_id){
        try {
            AcademicCourse academicCourse =  academicCourseService.add(course_id, academic_id);
            return ResponseEntity.ok(academicCourse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
