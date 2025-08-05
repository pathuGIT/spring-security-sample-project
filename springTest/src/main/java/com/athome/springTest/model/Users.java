package com.athome.springTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Enrollments> enrollment = new HashSet<>();

    public Users() {
    }

    public Users(int id, String username, String password, Role role, Set<Enrollments> enrollment) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enrollment = enrollment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Enrollments> getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Set<Enrollments> enrollment) {
        this.enrollment = enrollment;
    }
}



