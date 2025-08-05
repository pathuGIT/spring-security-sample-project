package com.athome.springTest.service;

import com.athome.springTest.model.Academic;
import com.athome.springTest.model.AcademicStatus;
import com.athome.springTest.repository.AcademicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicService {
    @Autowired
    private AcademicRepository academicRepository;

    public Academic createAcademic(Academic academic) {
        if(academicRepository.existsByStartDate(academic.getStartDate())){
            throw new IllegalArgumentException("Course name already exists");
        }
        return academicRepository.save(academic);
    }

    public List<Academic> getAcademics(String status) {
        if ("all".equalsIgnoreCase(status)) {
            return academicRepository.findAll();
        }
        AcademicStatus enumStatus = AcademicStatus.valueOf(status.toUpperCase());
        return academicRepository.findAllByAcademicStatus(enumStatus);
    }

}
