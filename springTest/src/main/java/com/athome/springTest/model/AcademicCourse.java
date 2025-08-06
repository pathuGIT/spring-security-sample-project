package com.athome.springTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference("academic–courses")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "acdemic_id")
    @JsonBackReference("course–academics")
    private Academic academic;

    @OneToMany(mappedBy = "academicCourse", cascade = CascadeType.ALL)
    @JsonManagedReference("ac-course–enrollments")
    private Set<Enrollments> enrollment = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Academic getAcademic() {
        return academic;
    }

    public void setAcademic(Academic academic) {
        this.academic = academic;
    }

    public Set<Enrollments> getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Set<Enrollments> enrollment) {
        this.enrollment = enrollment;
    }
}
