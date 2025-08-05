package com.athome.springTest.repository;

import com.athome.springTest.model.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Integer> {
    boolean existsByStartDate(Date startDate);
}
