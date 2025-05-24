# Use official Tomcat base image with JDK 17
FROM tomcat:9.0-jdk17-temurin

# Clean default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file into the ROOT path
COPY target/UserAccessManagementSystem.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat (CMD already defined in base image)
