# Stage 1: Build the application with Gradle
FROM gradle:8.6-jdk17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle configuration files
COPY ../build.gradle ../settings.gradle ./

# Copy the source code
COPY ../src ./src

# Build the application (produces a JAR file)
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port Spring Boot runs on (default: 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]