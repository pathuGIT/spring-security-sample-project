package com.athome.springTest.service;

import com.athome.springTest.model.AcademicCourse;
import com.athome.springTest.model.Enrollments;
import com.athome.springTest.model.Users;
import com.athome.springTest.repository.AcademicCourseRepository;
import com.athome.springTest.repository.AcademicRepository;
import com.athome.springTest.repository.EnrollRepository;
import com.athome.springTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnrollService {

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private AcademicCourseRepository academicCourseRepository;

    @Autowired
    private UserRepository userRepository;

    public Enrollments enroll(int academicCourse_id) {
        // Get authentication object from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get username (usually the principal's name)
        String username = authentication.getName();

        AcademicCourse academicCourse = academicCourseRepository.findById(academicCourse_id)
                .orElseThrow(()-> new IllegalArgumentException("Not pound this academicCourse id: " + academicCourse_id));
        Users user = userRepository.findByUsername(username);

        Enrollments en = Enrollments.builder()
                .academicCourse(academicCourse)
                .users(user)
                .enrolledAt(new Date())
                .build();

        return enrollRepository.save(en);
    }
}
