package com.athome.springTest.service;

import com.athome.springTest.model.Academic;
import com.athome.springTest.model.Course;
import com.athome.springTest.repository.AcademicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
