package servlets.api.auth;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        try {
            Connection connection = dataSource.getConnection();
            boolean success = User.register(connection, request);
            if (success) {
                // Registration succeeded
                response.sendRedirect("/Auth?action=viewLogin");
            } else {
                // Registration failed
                response.sendRedirect("/Auth?action=viewRegister");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/Auth?action=viewRegister");
        }
    }
}
