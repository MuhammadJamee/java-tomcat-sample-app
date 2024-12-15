package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/hello-servlet/hello")  // Map servlet to /hello-servlet/hello for probes
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For liveness and readiness probes, respond with "OK"
        String hostname = System.getenv("HOSTNAME");  // Will work if the environment variable is set in the container

        // In case the HOSTNAME environment variable is not available, use the server's local name
        if (hostname == null) {
            hostname = request.getLocalName();
        }

        // Get client IP address (useful for debugging and verifying load balancing)
        String clientIP = request.getRemoteAddr();

        // Set content type for regular requests
        response.setContentType("text/html");

        // Write HTML content for regular requests (only if you want to see this in the response)
        response.getWriter().write("<h1>Hello, World from Servlet!</h1>");
        response.getWriter().write("<p>Request handled by server: " + hostname + "</p>");
        response.getWriter().write("<p>Client IP: " + clientIP + "</p>");
        response.getWriter().write("<p>Request URI: " + request.getRequestURI() + "</p>");

        // For liveness and readiness probes, we respond with a simple "OK" message
        if (request.getRequestURI().endsWith("/hello")) {
            // Set HTTP 200 OK response for health check
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("OK");  // Minimal response for the probes
        } else {
            // Regular requests to the servlet will receive the full response
            System.out.println("Hi!");  // This will log to the console (useful for debugging)
        }
    }
}
