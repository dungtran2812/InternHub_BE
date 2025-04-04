# Stage 1: Build the application
FROM gradle:jdk17-jammy AS build

# Set the working directory
WORKDIR /home/gradle/src

# Copy all project files
COPY . .

# Build the application
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-jammy

# Expose the application port
EXPOSE 8080

# Set the working directory (creates /app automatically)
WORKDIR /app

# Copy the built JAR file
COPY --from=build /home/gradle/src/build/libs/*-SNAPSHOT.jar /app/spring-boot-application.jar

# Run the application with optimized JVM settings
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/spring-boot-application.jar"]