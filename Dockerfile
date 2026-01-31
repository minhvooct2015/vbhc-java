# Use a small JRE image
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside container
WORKDIR /app

## Copy your built JAR into the image
#COPY /target/doc-management-service-1.0.0-SNAPSHOT.jar app.jar
# Copy the Quarkus fast-jar output directory
COPY target/quarkus-app/ ./

# Expose the HTTP port your app listens on
EXPOSE 9000
# Expose the HTTP port used by your app (change if not 8080)
# Environment variables for DB connection
ENV DB_HOST=doc-database-pg \
    DB_PORT=5432 \
    DB_NAME=vbhcdb \
    DB_USER=postgres \
    APP_IMAGE_DIRECTORY=/opt/app/images\
    DB_PASSWORD=123456


# Create the images directory inside the container
RUN mkdir -p /opt/app/images
# Command to run the JAR
CMD ["java", "-jar", "quarkus-run.jar"]