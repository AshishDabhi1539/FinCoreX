package com.banking.exception;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class GlobalExceptionHandler extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleError(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleError(request, response);
    }
    
    private void handleError(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get error information
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        String requestURI = (String) request.getAttribute("javax.servlet.error.request_uri");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Generate error page
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <title>Error - Banking Application</title>");
        out.println("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("    <style>");
        out.println("        body { background: #f8f9fa; }");
        out.println("        .error-container {");
        out.println("            min-height: 100vh;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            justify-content: center;");
        out.println("        }");
        out.println("        .error-card {");
        out.println("            max-width: 600px;");
        out.println("            border-radius: 15px;");
        out.println("            box-shadow: 0 4px 6px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class=\"error-container\">");
        out.println("        <div class=\"card error-card\">");
        out.println("            <div class=\"card-header bg-danger text-white text-center\">");
        out.println("                <h3><i class=\"fas fa-exclamation-triangle\"></i> Error Occurred</h3>");
        out.println("            </div>");
        out.println("            <div class=\"card-body\">");
        
        if (statusCode != null) {
            out.println("                <h5 class=\"card-title text-danger\">Status Code: " + statusCode + "</h5>");
        }
        
        if (errorMessage != null && !errorMessage.isEmpty()) {
            out.println("                <p class=\"card-text\"><strong>Error:</strong> " + errorMessage + "</p>");
        }
        
        if (requestURI != null) {
            out.println("                <p class=\"card-text\"><strong>Request URI:</strong> " + requestURI + "</p>");
        }
        
        if (exception != null) {
            out.println("                <div class=\"alert alert-warning\">");
            out.println("                    <strong>Exception Details:</strong><br>");
            out.println("                    <small>" + exception.getClass().getSimpleName() + ": " + exception.getMessage() + "</small>");
            out.println("                </div>");
        }
        
        out.println("                <div class=\"text-center mt-4\">");
        out.println("                    <a href=\"" + request.getContextPath() + "/login\" class=\"btn btn-primary me-2\">");
        out.println("                        <i class=\"fas fa-home\"></i> Go to Login");
        out.println("                    </a>");
        out.println("                    <button onclick=\"history.back()\" class=\"btn btn-secondary\">");
        out.println("                        <i class=\"fas fa-arrow-left\"></i> Go Back");
        out.println("                    </button>");
        out.println("                </div>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\"></script>");
        out.println("    <script src=\"https://kit.fontawesome.com/your-fontawesome-kit.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }
}

