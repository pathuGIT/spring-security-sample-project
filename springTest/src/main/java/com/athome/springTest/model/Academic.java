package com.athome.springTest.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Academic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AcademicStatus academicStatus;

    private Date startYear;
    private Date endYear;

    public Academic() {
    }

    public Academic(int id, AcademicStatus academicStatus, Date startYear, Date endYear) {
        this.id = id;
        this.academicStatus = academicStatus;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AcademicStatus getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(AcademicStatus academicStatus) {
        this.academicStatus = academicStatus;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }
}
