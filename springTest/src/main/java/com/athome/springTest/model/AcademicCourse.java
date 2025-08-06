package com.athome.springTest.model;

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
    private Course course;

    @ManyToOne
    @JoinColumn(name = "acdemic_id")
    private Academic academic;

    @OneToMany(mappedBy = "academicCourse", cascade = CascadeType.ALL)
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
