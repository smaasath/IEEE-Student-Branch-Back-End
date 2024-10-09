# Use a base image with Java
FROM openjdk:22

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/IEEE-Student-Branch-Back-End-1.0-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
