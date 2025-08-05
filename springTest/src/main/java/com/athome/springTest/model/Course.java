package com.athome.springTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int co_id;
    private String name;
    private String type;
    private int credit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Enrollments> enrollment = new HashSet<>();

    public Course() {
    }

    public Course(int co_id, String name, String type, int credit, Set<Enrollments> enrollment) {
        this.co_id = co_id;
        this.name = name;
        this.type = type;
        this.credit = credit;
        this.enrollment = enrollment;
    }

    public int getCo_id() {
        return co_id;
    }

    public void setCo_id(int co_id) {
        this.co_id = co_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Set<Enrollments> getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Set<Enrollments> enrollment) {
        this.enrollment = enrollment;
    }
}
