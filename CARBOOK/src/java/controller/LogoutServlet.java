package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LogoutServlet - Handles user logout
 * @author
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
<<<<<<< HEAD
        // Invalidate session
=======
      
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
<<<<<<< HEAD
        // Redirect to login page
=======
      
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        response.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Logout Servlet for CARBOOK";
    }
}
