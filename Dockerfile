# Stage 1: Build the application
FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

# Copy pom.xml and download dependencies first (for Docker build caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
