package com.athome.springTest.repository;

import com.athome.springTest.model.AcademicCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicCourseRepository extends JpaRepository<AcademicCourse, Integer> {
}
