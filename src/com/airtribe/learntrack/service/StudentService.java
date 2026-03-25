package com.airtribe.learntrack.service;

import java.util.ArrayList;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;

public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void addStudent(String firstName, String lastName, String email, String batch) {
        Student newStudent = new Student(firstName, lastName, email, batch, true);
        studentRepository.save(newStudent);
    }

    public Student getStudentById(String id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student", id);
        }
        return student;
    }

    public void removeStudent(String id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    public void updateStudent(String id, Student updatedStudent) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setBatch(updatedStudent.getBatch());
        student.setActive(updatedStudent.isActive());
    }

    public ArrayList<Student> listStudents() {
        return studentRepository.findAll();
    }
}
