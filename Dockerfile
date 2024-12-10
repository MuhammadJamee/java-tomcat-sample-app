# Step 1: Build the WAR file using Maven
FROM maven:3.8.6-openjdk-11-slim AS build

# Set the working directory
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the WAR file
RUN mvn clean package -DskipTests

# Step 2: Copy the WAR file into Tomcat
FROM tomcat:9.0

# Copy the WAR file from the build stage to the Tomcat webapps folder
COPY --from=build /app/target/hello-servlet.war /usr/local/tomcat/webapps/

# Expose Tomcat's default port
EXPOSE 8080

# Run Tomcat when the container starts
CMD ["catalina.sh", "run"]
