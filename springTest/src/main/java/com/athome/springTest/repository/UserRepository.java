package com.athome.springTest.repository;

import com.athome.springTest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}

