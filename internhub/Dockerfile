# Stage 1: Build the application
FROM gradle:jdk17-jammy AS build
WORKDIR /home/gradle/src
COPY . .
RUN gradle build -x test --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy
EXPOSE 8080
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/spring-boot-application.jar"]