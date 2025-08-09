package com.athome.springTest.repository;

import com.athome.springTest.model.Academic;
import com.athome.springTest.model.AcademicStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Integer> {
    boolean existsByStartDate(Date startDate);
    List<Academic> findAllByAcademicStatus(AcademicStatus status);
}

