package com.airtribe.learntrack;

import java.util.Scanner;

import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentRepository studentRepository = new StudentRepository();
        StudentService studentService = new StudentService(studentRepository);

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);

        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepository, courseService,
                studentService);
        ConsoleUI consoleUI = new ConsoleUI(studentService, courseService, enrollmentService, scanner);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== LearnTrack ===");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        consoleUI.studentMenu();
                        break;
                    case 2:
                        consoleUI.courseMenu();
                        break;
                    case 3:
                        consoleUI.enrollmentMenu();
                        break;
                    case 4:
                        isRunning = false;
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid menu item.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        scanner.close();
    }
}