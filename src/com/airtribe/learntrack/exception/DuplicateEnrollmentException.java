package com.airtribe.learntrack.exception;

public class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String studentId, String courseId) {
        super("Enrollment already exists for Student " + studentId + " in Course " + courseId);
    }
}
