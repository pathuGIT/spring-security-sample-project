package com.athome.springTest.repository;

import com.athome.springTest.model.AcademicCourse;
import com.athome.springTest.model.AcademicStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicCourseRepository extends JpaRepository<AcademicCourse, Integer> {
    List<AcademicCourse> findByAcademic_AcademicStatus(AcademicStatus status);
}
