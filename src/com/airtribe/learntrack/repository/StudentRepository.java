package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import com.airtribe.learntrack.entity.Student;

public class StudentRepository {
    private ArrayList<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
    }

    public void save(Student student) {
        students.add(student);
    }

    public Student findById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> findAll() {
        return students;
    }

    public void delete(Student student) {
        students.remove(student);
    }
}
