package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/Auth")
public class AuthControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call the appropriate method based on the request parameters or URL
        String action = request.getParameter("action");
        if ("viewLogin".equals(action)) {
            viewLogin(request, response);
        } else if ("viewRegister".equals(action)) {
            viewRegister(request, response);
        } else {
            viewLogin(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            login(request, response);
        } else if ("register".equals(action)) {
            register(request, response);
        } else if ("logout".equals(action))  {
            logout(request, response);
        }
    }


    private void viewLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/LoginServlet").forward(request, response);
    }

    private void viewRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/RegisterServlet").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/LogoutServlet").forward(request, response);
    }
}
