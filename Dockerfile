# Stage 1: Build WAR file using Maven
FROM maven:3.8.5-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Package the WAR file
RUN mvn clean package

# Stage 2: Run WAR in Tomcat
FROM tomcat:9.0-jdk17-temurin

# Set environment variable
ENV COMMENT="You're good"

# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file from build stage
COPY --from=build /app/target/UserAccessManagementSystem.war /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat port
EXPOSE 8080
