package com.springhow.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@SpringBootApplication
public class HelloWorldApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HelloWorldApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

    @RequestMapping("/")
    public String helloWorld(HttpServletRequest request) {
        String hostname;
        String clientIP = request.getRemoteAddr();  // Get client IP address
        String requestURI = request.getRequestURI();  // Get request URI

        try {
            // Get the hostname of the server handling the request
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "Unknown Host";
        }

        // Return HTML content with hostname, client IP, and request URI for load balancing verification
        return "<h1>Hello, World from Spring Boot! This is first change </h1>" +
               "<p>Request handled by server: " + hostname + "</p>" +
               "<p>Client IP: " + clientIP + "</p>" +
               "<p>Request URI: " + requestURI + "</p>";
    }
}
