# Stage 1: Build the Grails application using the Grails wrapper
FROM openjdk:11-jdk-slim AS build

# Set the working directory in the build stage
WORKDIR /app

# Copy the project files to the container
COPY . .

# Grant execute permissions to the Grails wrapper
RUN chmod +x grailsw

# Build the application in production mode, generating a WAR file
RUN ./grailsw war

# Stage 2: Run the Grails application in a lightweight JRE environment
FROM openjdk:11-jre-slim

# Set environment variables for production
ENV GRAILS_ENV=production
ENV APP_HOME=/app

# Create the app directory
WORKDIR $APP_HOME

# Copy the WAR file from the build stage to the final image
COPY --from=build /app/build/libs/*.war $APP_HOME/app.war

# Expose the application port (default Grails port is 8080)
EXPOSE 8080

# Run the application using the Java WAR launcher
CMD ["java", "-Dgrails.env=production", "-jar", "app.war"]

