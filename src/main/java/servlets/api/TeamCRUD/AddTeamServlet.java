package servlets.api.TeamCRUD;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;

@WebServlet("/AddTeamServlet")
public class AddTeamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        try {
            Connection connection = dataSource.getConnection();
            boolean success = Team.addTeam(connection, request);

            if (success) {
                session.setAttribute("success", "Team Added");
            } else {
                session.setAttribute("error", "Team Not Added");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid input format");
        } catch (SQLException e) {
            session.setAttribute("error", "Database Error");
        }

        response.sendRedirect("/Teams?action=viewTeams");
    }
}
