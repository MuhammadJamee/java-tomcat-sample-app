# Step 1: Build the WAR file using Maven
FROM maven:3.8.6-openjdk-11-slim AS build

# Set the working directory for the build process
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the WAR file using Maven, skipping tests (optional)
RUN mvn clean package -DskipTests

# Step 2: Copy the WAR file into Tomcat
FROM tomcat:9.0

# Create the tomcat user (if not already created in the base image)
RUN groupadd -r tomcat && useradd -r -g tomcat tomcat

# Ensure that the webapps directory is writable
RUN chmod -R 777 /usr/local/tomcat/webapps

# Remove default apps in Tomcat webapps folder (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file from the build stage to the Tomcat webapps folder
COPY --from=build /app/target/hello-servlet-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/hello-servlet.war

# Ensure Tomcat has permissions to write to the webapps directory
RUN chown -R tomcat:tomcat /usr/local/tomcat/webapps

USER tomcat

# Expose Tomcat's default HTTP port (8080)
EXPOSE 8080

# Run Tomcat when the container starts
CMD ["catalina.sh", "run"]
