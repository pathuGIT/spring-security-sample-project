package com.athome.springTest.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Academic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AcademicStatus academicStatus;

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "academic", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference("academic–courses")
    private Set<AcademicCourse> academicCourses = new HashSet<>();

}
