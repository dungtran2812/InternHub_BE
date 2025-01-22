FROM eclipse-temurin:17-jdk-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app
