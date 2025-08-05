package com.athome.springTest.controller;

import com.athome.springTest.model.Academic;
import com.athome.springTest.model.Course;
import com.athome.springTest.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic")
public class AcademicController {
    @Autowired
    private AcademicService academicService;

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createAcademic(@RequestBody Academic academic){
        try {
            Academic res = academicService.createAcademic(academic);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('SUB_ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<?> view_academics(@RequestParam String status){
        List<Academic> list =  academicService.getAcademics(status);
        return ResponseEntity.ok(list);
    }

}
