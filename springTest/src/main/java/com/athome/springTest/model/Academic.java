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

    private Date startDate;
    private Date endDate;

    public Academic() {
    }

    public Academic(int id, AcademicStatus academicStatus, Date startDate, Date endDate) {
        this.id = id;
        this.academicStatus = academicStatus;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
