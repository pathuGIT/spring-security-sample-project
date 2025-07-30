package com.athome.springTest.service;

import com.athome.springTest.model.Course;
import com.athome.springTest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        if(courseRepository.existsByName(course.getName())){
            throw new IllegalArgumentException("Course name already exists");
        }
        return courseRepository.save(course);
    }
}
