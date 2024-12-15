package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the hostname of the server handling the request
        String hostname = System.getenv("HOSTNAME"); // This will work if the environment variable is set in the container

        // In case the HOSTNAME environment variable is not available, use the server's local name
        if (hostname == null) {
            hostname = request.getLocalName();
        }

        // Get client IP address (useful for debugging and verifying load balancing)
        String clientIP = request.getRemoteAddr();

        // Set content type
        response.setContentType("text/html");

        // Write response with hostname and client IP for load balancing verification
        response.getWriter().write("<h1>Hello, World from Servlet!</h1>");
        response.getWriter().write("<p>Request handled by server: " + hostname + "</p>");
        response.getWriter().write("<p>Client IP: " + clientIP + "</p>");
        response.getWriter().write("<p>Request URI: " + request.getRequestURI() + "</p>");
    }
}
