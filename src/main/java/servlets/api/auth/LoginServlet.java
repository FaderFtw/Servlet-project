package servlets.api.auth;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Team;
import beans.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        try {
            Connection connection = dataSource.getConnection();
            User user = new User();
            boolean success = User.login(connection, request, user);
            
            if(success) {
                session.setAttribute("user", user);
                session.setAttribute("success", "Logged in Successfully");
                response.sendRedirect("/Teams?action=viewTeams");
            } else {
                response.sendRedirect("/Auth?action=viewLogin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/Auth?action=viewLogin");
        }
    }
}