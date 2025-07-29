package com.athome.springTest.repository;

import com.athome.springTest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);

    Users findByUsername(String username);
}
