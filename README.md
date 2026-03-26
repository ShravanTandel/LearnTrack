# LearnTrack

A console-based Student & Course Management System built using Core Java.

Admins can manage:
- **Students** — add, view, search by ID, activate/deactivate
- **Courses** — add, view, activate/deactivate
- **Enrollments** — enroll students in courses, view enrollments, mark as completed/cancelled

All data is stored in-memory using ArrayList (no database).

## Project Structure

```
src/com/airtribe/learntrack/
├── Main.java                        # Entry point
├── entity/                          # Data classes
│   ├── Person.java                  # Base class
│   ├── Student.java                 # Extends Person
│   ├── Trainer.java                 # Extends Person
│   ├── Course.java
│   └── Enrollment.java
├── enums/
│   └── EnrollmentStatus.java        # ACTIVE, COMPLETED, CANCELLED
├── repository/                      # In-memory data storage
│   ├── StudentRepository.java
│   ├── CourseRepository.java
│   └── EnrollmentRepository.java
├── service/                         # Business logic
│   ├── StudentService.java
│   ├── CourseService.java
│   └── EnrollmentService.java
├── ui/
│   └── ConsoleUI.java               # Menu-driven console interface
├── exception/
│   ├── EntityNotFoundException.java
│   ├── DuplicateEnrollmentException.java
│   └── InvalidInputException.java
└── util/
    ├── IdGenerator.java             # Static ID generation
    └── InputValidator.java          # Static input validation
```

## How to Compile and Run

From the project root (`LearnTrack/`):

```bash
# Compile all files
javac -d out -sourcepath src src/com/airtribe/learntrack/Main.java

# Run the application
java -cp out com.airtribe.learntrack.Main
```

## Technologies

- Java 21 (OpenJDK Temurin 21.0.2)
- No external libraries or frameworks
