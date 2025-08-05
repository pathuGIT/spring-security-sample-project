package com.athome.springTest.service;

import com.athome.springTest.repository.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollService {

    @Autowired
    private EnrollRepository enrollRepository;

}
