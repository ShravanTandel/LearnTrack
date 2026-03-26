package com.airtribe.learntrack.repository;

import java.util.ArrayList;
import com.airtribe.learntrack.entity.Course;

public class CourseRepository {
    private ArrayList<Course> courses;

    public CourseRepository() {
        courses = new ArrayList<>();
    }

    public void save(Course course) {
        courses.add(course);
    }

    public Course findById(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> findAll() {
        return courses;
    }

    public void delete(Course course) {
        courses.remove(course);
    }
}
