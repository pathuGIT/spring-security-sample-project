package com.athome.springTest.dto;

import com.athome.springTest.model.AcademicCourse;

public class AcademicCourseDTO {
    private int id;
    private int academicId;
    private int courseId;

    public AcademicCourseDTO(AcademicCourse academicCourse) {
        this.id = academicCourse.getId();
        this.academicId = academicCourse.getAcademic().getId();
        this.courseId = academicCourse.getCourse().getCo_id();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcademicId() {
        return academicId;
    }

    public void setAcademicId(int academicId) {
        this.academicId = academicId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
