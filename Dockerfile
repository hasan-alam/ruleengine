# Use a lightweight OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file into the image
COPY target/rule-engine-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (adjust if needed)
EXPOSE 8080

# Set the startup command
ENTRYPOINT ["java", "-jar", "app.jar"]
