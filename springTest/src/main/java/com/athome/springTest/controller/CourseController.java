package com.athome.springTest.controller;

import com.athome.springTest.model.Course;
import com.athome.springTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('SUB_ADMIN')")
    @PostMapping("/create_course")
    public ResponseEntity<?> create_course(@RequestBody Course course) {
        try {
            Course res = courseService.createCourse(course);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('SUB_ADMIN') or hasAuthority('USER')")
    @GetMapping("/get_all_course")
    public ResponseEntity<?> get_all_course(){
        try {
            List<Course> res = courseService.AllCourse();
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('SUB_ADMIN') or hasAuthority('USER')")
    @GetMapping("/search_course_by")
    public ResponseEntity<?> search_course_by(@RequestParam String keyword){
        try {
            List<Course> res = courseService.AllCourseByKeyword(keyword);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{co_id}")
    public ResponseEntity<?> delete(@PathVariable int co_id){
        try {
            String res = courseService.deleteBy(co_id);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
