package servlets.api.auth;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("success", "Logged in Successfully");
                response.sendRedirect("/ListTeamsServlet");
            } else {
                response.sendRedirect("/Auth?action=viewLogin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/Auth?action=viewLogin");
        }
    }
}