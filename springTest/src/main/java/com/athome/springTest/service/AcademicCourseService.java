package com.athome.springTest.service;

import com.athome.springTest.model.Academic;
import com.athome.springTest.model.AcademicCourse;
import com.athome.springTest.model.Course;
import com.athome.springTest.repository.AcademicCourseRepository;
import com.athome.springTest.repository.AcademicRepository;
import com.athome.springTest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        AcademicCourse ac = new AcademicCourse();
        ac.setCourse(course);
        ac.setAcademic(academic);
        return academicCourseRepository.save(ac);
    }
}
