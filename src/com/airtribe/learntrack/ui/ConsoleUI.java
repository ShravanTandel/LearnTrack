package com.airtribe.learntrack.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.DuplicateEnrollmentException;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

public class ConsoleUI {
    private StudentService studentService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;
    private Scanner scanner;

    public ConsoleUI(StudentService studentService, CourseService courseService, EnrollmentService enrollmentService,
            Scanner scanner) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.scanner = scanner;
    }

    public void studentMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Deactivate/Activate Student");
            System.out.println("5. Back to Main Menu");
            System.out.println("Choose an option: ");

            try {

                int choice = Integer.parseInt(this.scanner.nextLine());

                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Enter student Details");
                            System.out.print("Enter student first name: ");
                            String firstName = InputValidator.validateNotEmpty(this.scanner.nextLine(), "First name");
                            System.out.print("Enter student last name: ");
                            String lastName = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Last name");
                            System.out.print("Enter student email: ");
                            String email = InputValidator.validateEmail(this.scanner.nextLine());
                            System.out.print("Enter student batch: ");
                            String batch = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Batch");

                            this.studentService.addStudent(firstName, lastName, email, batch);
                            System.out.println("Student added successfully.");
                        } catch (InvalidInputException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        ArrayList<Student> students = this.studentService.listStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                            break;
                        }

                        for (Student student : students) {
                            System.out.println(student.getDisplayName());
                        }
                        break;

                    case 3:
                        System.out.println("Enter student ID to search: ");
                        String studentId = this.scanner.nextLine();
                        try {
                            Student student = this.studentService.getStudentById(studentId);
                            System.out.println(student.getDisplayName());
                        } catch (EntityNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Enter student ID to deactivate/activate: ");
                        studentId = this.scanner.nextLine();
                        System.out.println("Enter new active status (yes/no): ");
                        boolean newActiveStatus = this.scanner.nextLine().equalsIgnoreCase("yes");
                        try {
                            if (newActiveStatus) {
                                this.studentService.activateStudent(studentId);
                            } else {
                                this.studentService.deactivateStudent(studentId);
                            }
                        } catch (EntityNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose a valid menu item.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void courseMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== Course Management =====");
            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate/Deactivate a course");
            System.out.println("4. Back to Main Menu");
            System.out.println("Choose an option:");

            try {

                int choice = Integer.parseInt(this.scanner.nextLine());

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter course name: ");
                            String name = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Course name");
                            System.out.print("Enter course description: ");
                            String description = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Description");
                            System.out.print("Enter course duration in weeks: ");
                            int durationInWeeks = InputValidator.validatePositiveInt(this.scanner.nextLine(), "Duration");
                            System.out.print("Is the course active? (yes/no): ");
                            boolean active = this.scanner.nextLine().equalsIgnoreCase("yes");

                            this.courseService.addCourse(name, description, durationInWeeks, active);
                            System.out.println("Course added successfully.");
                        } catch (InvalidInputException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        ArrayList<Course> courses = this.courseService.listCourses();
                        if (courses.isEmpty()) {
                            System.out.println("No courses found.");
                            break;
                        }

                        for (Course course : courses) {
                            System.out.println(course.describeCourse());
                        }

                        break;

                    case 3:
                        System.out.println("Enter course ID to activate/deactivate: ");
                        String courseId = this.scanner.nextLine();
                        System.out.println("Enter new active status (yes/no): ");
                        boolean newActiveStatus = this.scanner.nextLine().equalsIgnoreCase("yes");

                        try {
                            if (newActiveStatus) {
                                this.courseService.activateCourse(courseId);
                            } else {
                                this.courseService.deactivateCourse(courseId);
                            }
                        } catch (EntityNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose a valid menu item.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void enrollmentMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== Enrollment Management =====");
            System.out.println("1. Enroll a student in a course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. Mark enrollment as completed/cancelled");
            System.out.println("4. Back to main menu");
            System.out.println("Choose an option:");

            try {

                int choice = Integer.parseInt(this.scanner.nextLine());

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter student ID to enroll: ");
                            String studentId = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Student ID");
                            System.out.print("Enter course ID to enroll: ");
                            String courseId = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Course ID");
                            System.out.print("Enter enrollment date (YYYY-MM-DD): ");
                            String enrollmentDate = InputValidator.validateNotEmpty(this.scanner.nextLine(), "Enrollment date");

                            this.enrollmentService.addStudentToCourse(studentId, courseId, enrollmentDate);
                            System.out.println("Student enrolled successfully.");
                        } catch (InvalidInputException | EntityNotFoundException | DuplicateEnrollmentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Enter student ID to view enrollments: ");
                        String studentId = this.scanner.nextLine();

                        ArrayList<Enrollment> enrollmentsForStudentId = this.enrollmentService
                                .getEnrollmentsForStudentId(studentId);

                        if (enrollmentsForStudentId.isEmpty()) {
                            System.out.println("No enrollments found for this student.");
                            break;
                        }

                        for (Enrollment enrollment : enrollmentsForStudentId) {
                            System.out.println(enrollment.describeEnrollment());
                        }

                        break;
                    case 3:
                        System.out.println("Enter enrollment Id to update:");
                        String enrollmentId = this.scanner.nextLine();
                        System.out.println("Enter new status (1 for completed/2 for cancelled): ");
                        String newStatus = this.scanner.nextLine();

                        try {

                            if (newStatus.equalsIgnoreCase("1")) {

                                this.enrollmentService.markAsCompleted(enrollmentId);

                            } else if (newStatus.equalsIgnoreCase("2")) {

                                this.enrollmentService.markAsCancelled(enrollmentId);

                            } else {
                                System.out.println("Invalid status. Please enter '1' or '2'.");
                            }

                        }

                        catch (EntityNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid menu item.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
