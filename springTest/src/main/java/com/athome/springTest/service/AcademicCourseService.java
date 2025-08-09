package com.athome.springTest.service;

import com.athome.springTest.dto.AcademicCourseDTO;
import com.athome.springTest.model.Academic;
import com.athome.springTest.model.AcademicCourse;
import com.athome.springTest.model.AcademicStatus;
import com.athome.springTest.model.Course;
import com.athome.springTest.repository.AcademicCourseRepository;
import com.athome.springTest.repository.AcademicRepository;
import com.athome.springTest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AcademicCourseService {
    @Autowired
    private AcademicCourseRepository academicCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AcademicRepository academicRepository;

    public AcademicCourse add(int courseId, int academicId) {
        Academic academic = academicRepository.findById(academicId)
                .orElseThrow(()-> new IllegalArgumentException("Not pound this academic id: " + academicId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new IllegalArgumentException("Not pound this course id: " + courseId));

        AcademicCourse ac = AcademicCourse.builder()
                .course(course)
                .academic(academic)
                .build();
        return academicCourseRepository.save(ac);
    }


    public List<AcademicCourseDTO> getAll() {

        // Get authentication object from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get roles (authorities)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // You can also check if user has a specific role
        boolean isUser = authorities.stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));

        List<AcademicCourse> list =  getAcademicCoursesForUser(isUser);

        List<AcademicCourseDTO> academicCourseDTOS = new ArrayList<>();
        for(AcademicCourse ac : list)
            academicCourseDTOS.add(new AcademicCourseDTO(ac));

        return academicCourseDTOS;
    }

    public List<AcademicCourse> getAcademicCoursesForUser(boolean isUser) {
        if (isUser) {
            return academicCourseRepository.findByAcademic_AcademicStatus(AcademicStatus.NOT_STARTED);
        }
        return academicCourseRepository.findAll();
    }
}


