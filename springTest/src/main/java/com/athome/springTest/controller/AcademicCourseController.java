package com.athome.springTest.controller;

import com.athome.springTest.model.AcademicCourse;
import com.athome.springTest.service.AcademicCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> getAllAcademicCourses(){
        List<AcademicCourse> academicCourses = academicCourseService.getAll();
        return ResponseEntity.ok(academicCourses);
    }
}
