# Use a base image with JDK 17
FROM openjdk:17-jdk-slim

# Add a volume to store logs
VOLUME /tmp

# Copy the application's jar to the container
ARG JAR_FILE=target/SampleApp-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
