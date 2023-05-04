FROM openjdk:17-jdk-alpine


# Set the working directory
WORKDIR /app

# Copy the Maven wrapper from the project to the Docker image
COPY mvnw .
COPY .mvn .mvn

# Copy the source code and the pom.xml file
COPY src src
COPY pom.xml .

# Build the application using Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "target/petminder-api-0.0.1-SNAPSHOT.jar"]