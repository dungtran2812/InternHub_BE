# Stage 1: Build the application
FROM gradle:jdk17-jammy AS build

# Set the working directory
WORKDIR /home/gradle/src

# Copy all project files from the current directory
COPY . .

# Build the application, skipping tests
RUN gradle build -x test --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy

# Expose the application port
EXPOSE 8080

# Set the working directory
WORKDIR /app

# Copy the built JAR file
COPY --from=build /home/gradle/src/build/libs/internhub.jar /app/spring-boot-application.jar

# Run the application with optimized JVM settings
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/spring-boot-application.jar"]