FROM maven:3.8.6-openjdk-8-slim AS build


# Step 1: Build the WAR file using Maven
#FROM maven:3.8.6-openjdk-8-slim AS build

# Set the working directory for the build process
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the WAR file using Maven, skipping tests (optional)
RUN mvn clean package -DskipTests

# Step 2: Copy the WAR file into Tomcat
FROM tomcat:9.0

# Remove default apps in Tomcat webapps folder (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file from the build stage to the Tomcat webapps folder
COPY --from=build /app/target/hello-world-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

# Expose Tomcat's default HTTP port (8080)
EXPOSE 8080

# Run Tomcat when the container starts
CMD ["catalina.sh", "run"]