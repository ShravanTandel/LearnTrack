package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {
    private String id;
    private String studentId;
    private String courseId;
    private String enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(String id, String studentId, String courseId, String enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public String describeEnrollment() {
        return "Enrollment ID: " + id + ", Student ID: " + studentId + ", Course ID: " + courseId +
                ", Enrollment Date: " + enrollmentDate + ", Status: " + status;
    }
}
