package com.athome.springTest.service;

import com.athome.springTest.model.Course;
import com.athome.springTest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Course> AllCourse() {
        List<Course> courseList = courseRepository.findAll();
        if(courseList.isEmpty()){
            throw new IllegalArgumentException("Empty course list");
        }
        return courseList;
    }

    public List<Course> AllCourseByKeyword(String keyword) {
        List<Course> courseList = courseRepository.findByKeyword(keyword);
        if(courseList.isEmpty()){
            throw new IllegalArgumentException("Not search result");
        }
        return courseList;
    }

    public String deleteBy(int coId) {
        Course course = courseRepository.findById(coId).orElseThrow(
                ()-> new IllegalArgumentException("Course not found with ID: " + coId));

        courseRepository.deleteById(course.getCo_id());
        return "successfully deleted course by id: " + course.getCo_id();
    }
}
