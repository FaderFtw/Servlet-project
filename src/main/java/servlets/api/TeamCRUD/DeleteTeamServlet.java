package servlets.api.TeamCRUD;

import beans.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/DeleteTeamServlet")
public class DeleteTeamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        try {
            Connection connection = dataSource.getConnection();
            boolean success = Team.deleteTeam(connection, request);

            if (success) {
                session.setAttribute("success", "Team Deleted");
            } else {
                session.setAttribute("error", "Failed to delete team");
            }
        } catch (SQLException e) {
            session.setAttribute("error", "Failed to delete team");
        }

        response.sendRedirect("/Teams?action=viewTeams");
    }

}
