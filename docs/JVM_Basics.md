# JVM Basics

## What is JDK, JRE, and JVM?

- **JVM (Java Virtual Machine)** — The engine that actually runs your Java program. It reads the compiled bytecode (`.class` files) and translates it into machine instructions that your operating system understands. Every platform (Windows, macOS, Linux) has its own JVM implementation, but they all understand the same bytecode.

- **JRE (Java Runtime Environment)** — The JVM plus the standard libraries (like `String`, `ArrayList`, `System.out`, etc.) that your program needs at runtime. If you only want to *run* Java programs, the JRE is enough.

- **JDK (Java Development Kit)** — The JRE plus development tools like the compiler (`javac`), debugger, and other utilities. If you want to *write and compile* Java programs, you need the JDK.


## What is Bytecode?

When you compile a `.java` file using `javac`, it does not produce machine code specific to your OS. Instead, it produces **bytecode** — an intermediate set of instructions stored in `.class` files. Bytecode is not tied to any particular operating system or CPU. It is designed to be read and executed by the JVM.

## What Does "Write Once, Run Anywhere" Mean?

Because `javac` compiles your code into platform-independent bytecode (not into Windows `.exe` or macOS binaries), the same `.class` file can run on any machine that has a JVM installed. You write your code once on macOS, compile it once, and that same compiled output runs on a Windows PC, a Linux server, or any other system with a compatible JVM.
