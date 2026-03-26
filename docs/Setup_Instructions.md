# Setup Instructions

## JDK Version Used

- **JDK**: OpenJDK 21.0.2 (Temurin-21.0.2+13 LTS)
- **OS**: macOS (Darwin)
- **Build Tool**: Command-line (`javac` and `java`)

## How to Verify Your Installation

Open a terminal and run:

```bash
java -version
javac -version
```

You should see output similar to:

```
openjdk version "21.0.2" 2024-01-16 LTS
OpenJDK Runtime Environment Temurin-21.0.2+13 (build 21.0.2+13-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.2+13 (build 21.0.2+13-LTS, mixed mode)

javac 21.0.2
```

## Running the Hello World Program

The Hello World source file is located at `src/com/learntrack/HelloWorld.java`.

### Step 1 — Compile

From the project root directory, compile the file:

```bash
javac -d out src/com/learntrack/HelloWorld.java
```

This creates the compiled `.class` file inside the `out/` directory, preserving the package structure:

```
out/
  com/
    learntrack/
      HelloWorld.class
```

### Step 2 — Run

Run the compiled class using its fully qualified name:

```bash
java -cp out com.learntrack.HelloWorld
```

### Expected Output

```
Hello World
```

### What Happened

1. `javac` compiled `HelloWorld.java` (source code) into `HelloWorld.class` (bytecode).
2. `java` launched the JVM, which loaded the bytecode and executed the `main` method.
3. The message `Hello World` was printed to the console.
