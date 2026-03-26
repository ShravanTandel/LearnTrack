package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getCourseById(String courseId) throws EntityNotFoundException {
        Course course = courseRepository.findById(courseId);

        if (course == null) {
            throw new EntityNotFoundException("Course", courseId);
        }

        return course;
    }

    public void addCourse(String name, String description, int durationInWeeks, boolean active) {
        Course newCourse = new Course(IdGenerator.getNextCourseId(), name, description, durationInWeeks, active);
        courseRepository.save(newCourse);
    }

    public void deleteCourse(String id) throws EntityNotFoundException {
        Course course = getCourseById(id);

        courseRepository.delete(course);
    }

    public void activateCourse(String id) throws EntityNotFoundException {
        Course course = getCourseById(id);

        course.setActive(true);
    }

    public void deactivateCourse(String id) throws EntityNotFoundException {

        Course course = getCourseById(id);

        course.setActive(false);
    }

    public ArrayList<Course> listCourses() {
        return courseRepository.findAll();
    }
}
