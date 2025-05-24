# Use Maven image to build the app
FROM maven:3.8.5-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the full source
COPY src ./src

# Package the app as a shaded (fat) JAR
RUN mvn clean package

# Use smaller base image to run the jar
FROM eclipse-temurin:17-jdk-jammy

# Working directory in runtime container
WORKDIR /app

# Copy the built jar from previous stage
COPY --from=build /app/target/UserAccessManagementSystem.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
