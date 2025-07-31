package com.athome.springTest.repository;

import com.athome.springTest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    boolean existsByName(String name);

    @Query("SELECT c FROM Course c " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.type) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR CAST(c.credit AS string) LIKE CONCAT('%', :keyword, '%') " +
            "OR CAST(c.co_id AS string) LIKE CONCAT('%', :keyword, '%')")
    List<Course> findByKeyword(@Param("keyword") String keyword);

}
