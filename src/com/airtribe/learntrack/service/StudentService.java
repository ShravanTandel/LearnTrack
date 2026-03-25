package com.airtribe.learntrack.service;

import java.util.ArrayList;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public class StudentService {
    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public Student getStudentForId(String id) {
        for(Student student: students) {
            if(student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStudent(String firstName, String lastName, String email, String batch) {
        Student newStudent = new Student(firstName, lastName, email, batch, true);
        students.add(newStudent);
    }

    public void removeStudent(String id) throws EntityNotFoundException {
        Student student = getStudentForId(id);

        if(student == null) {
            throw new EntityNotFoundException("Student", id);
        }

        students.remove(student);
    }

    public void updateStudent(String id, Student updatedStudent) throws EntityNotFoundException {
        Student student = getStudentForId(id);
        if(student == null) {
            throw new EntityNotFoundException("Student", id);
        }

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setBatch(updatedStudent.getBatch());
        student.setActive(updatedStudent.isActive());
    }

    public ArrayList<Student> listStudents() {
        return students;
    }
}
