package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {Github
        // Get the hostname of the server handling the request
        String hostname = System.getenv("HOSTNAME"); // This will work if the environment variable is set in the container

        // In case the HOSTNAME environment variable is not available, use the server's local name
        if (hostname == null) {
            hostname = request.getLocalName();
        }

        // Set content type
        response.setContentType("text/html");

        // Write response with hostname for load balancing verification
        response.getWriter().write("<h1>Hello, World from Servlet! Rollback </h1>");
        response.getWriter().write("<p>Request handled by server: " + hostname + "</p>");
    }
}
