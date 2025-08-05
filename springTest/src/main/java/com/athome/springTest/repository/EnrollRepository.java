package com.athome.springTest.repository;

import com.athome.springTest.model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<Enrollments, Integer> {
}
