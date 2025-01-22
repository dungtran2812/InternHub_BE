# Use a base image with Java 17
FROM eclipse-temurin:17-jdk-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy Gradle wrapper and configuration files to cache dependencies
COPY gradle/ gradle/
COPY gradlew settings.gradle build.gradle* /app/

# Make Gradle wrapper executable
RUN chmod +x ./gradlew

# Cache dependencies
RUN ./gradlew dependencies --no-daemon

# Copy the entire project
COPY . .

# Build the application
RUN ./gradlew build --no-daemon

# Use a minimal base image for the final stage
FROM eclipse-temurin:17-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built application JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
