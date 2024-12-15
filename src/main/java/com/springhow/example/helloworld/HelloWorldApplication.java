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

    // Return a cleaner HTML with CSS for styling
    return "<html>" +
            "<head>" +
            "<title>Hello, World from Spring Boot</title>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; margin: 0; padding: 0; }" +
            ".container { width: 80%; max-width: 800px; margin: 0 auto; padding: 20px; text-align: center; }" +
            "h1 { color: #4CAF50; font-size: 36px; }" +
            "p { font-size: 18px; line-height: 1.6; color: #555; }" +
            ".info { background-color: #ffffff; border: 1px solid #ddd; border-radius: 8px; padding: 20px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); }" +
            ".info p { margin: 10px 0; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='container'>" +
            "<h1>Hello, World from Spring Boot!</h1>" +
            "<div class='info'>" +
            "<p><strong>Request handled by server:</strong> " + hostname + "</p>" +
            "<p><strong>Client IP:</strong> " + clientIP + "</p>" +
            "<p><strong>Request URI:</strong> " + requestURI + "</p>" +
            "</div>" +
            "</div>" +
            "</body>" +
            "</html>";
}
