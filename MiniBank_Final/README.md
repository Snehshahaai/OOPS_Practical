# MiniBank - Beginner Version

This is the final integrated MiniBank project from the OOPJ practical list.

## Requirements
- JDK 17+
- Command Prompt / Terminal
- JUnit 5 only for the test file

## Compile and run

Windows:
1. Open this folder.
2. Run `build.bat`.
3. Run `java -jar minibank.jar`.

Or compile manually:

```text
javac -d out src\model\annotation\*.java src\exception\*.java src\model\*.java src\util\*.java src\service\*.java src\app\*.java
jar cfe minibank.jar app.MiniBank -C out .
java -jar minibank.jar
```

## Main features covered
- Classes and objects
- Encapsulation
- Constructors and `this()`
- `toString`, `equals`, `hashCode`, `clone`
- Abstract class and inheritance
- Polymorphism
- Interfaces, default method, functional interface, lambda, anonymous class, marker interface
- Packages and static import concepts
- Custom annotations and reflection
- Custom checked exceptions
- `try-catch-finally` and try-with-resources
- Threads, synchronization and thread pool
- Serialization
- NIO `Path` / `Files`
- Collections, Comparable and Comparator
- Generics and `@SafeVarargs`
- Repository using `@Id`
- Layered model/service/application structure
- JUnit 5 example
- Runnable JAR
