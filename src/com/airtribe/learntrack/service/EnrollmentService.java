package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.DuplicateEnrollmentException;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    private CourseService courseService;
    private StudentService studentService;

    public EnrollmentService (EnrollmentRepository enrollmentRepository, CourseService courseService, StudentService studentService) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public Enrollment getEnrollmentById(String enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        if(enrollment == null) {
            throw new EntityNotFoundException("Enrollment", enrollmentId);
        }

        return enrollment;
    }

    private boolean enrollmentExists(String studentId, String courseId) {
        for (Enrollment enrollment : enrollmentRepository.findAll()) {
            if (enrollment.getStudentId().equals(studentId) && enrollment.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public void addStudentToCourse(String studentId, String courseId, String enrollmentDate) throws EntityNotFoundException, DuplicateEnrollmentException {
        studentService.getStudentById(studentId);
        courseService.getCourseById(courseId);

        if(enrollmentExists(studentId, courseId)) {
            throw new DuplicateEnrollmentException(studentId, courseId);
        }

        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE);
        enrollmentRepository.save(enrollment);
    }

    public void markAsCompleted(String enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);

        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void markAsCancelled(String enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);

        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

    public ArrayList<Enrollment> getEnrollmentsForStudentId(String studentId) {
        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();

        for(Enrollment enrollment : enrollmentRepository.findAll()) {
            if(enrollment.getStudentId().equals(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }

        return studentEnrollments;
    }
}
