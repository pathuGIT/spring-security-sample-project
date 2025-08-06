package com.athome.springTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int co_id;
    private String name;
    private String type;
    private int credit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference("course–academics")
    private Set<AcademicCourse> academicCourses = new HashSet<>();

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

    public Set<AcademicCourse> getAcademicCourses() {
        return academicCourses;
    }

    public void setAcademicCourses(Set<AcademicCourse> academicCourses) {
        this.academicCourses = academicCourses;
    }
}
