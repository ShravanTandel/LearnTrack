package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import com.airtribe.learntrack.entity.Enrollment;

public class EnrollmentRepository {
    private ArrayList<Enrollment> enrollments;

    public EnrollmentRepository() {
        enrollments = new ArrayList<>();
    }

    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public Enrollment findById(String id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(id)) {
                return enrollment;
            }
        }
        return null;
    }

    public ArrayList<Enrollment> findAll() {
        return enrollments;
    }

    public void delete(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }
}
