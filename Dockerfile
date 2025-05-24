# Use a Java base image with Maven
FROM maven:3.8.5-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source files
COPY src ./src

# Build the application
RUN mvn clean package

# Use a smaller runtime image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/UserAccessManagementSystem-1.0-SNAPSHOT-shaded.jar app.jar

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
