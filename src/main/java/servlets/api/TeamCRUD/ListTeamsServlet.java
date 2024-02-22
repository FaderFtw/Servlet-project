package servlets.api.TeamCRUD;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;

@WebServlet("/ListTeamsServlet")
public class ListTeamsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Team> teams = new ArrayList<>();

        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");
        try {
            Connection connection = dataSource.getConnection();
            Team.all(connection, request, teams);
            session.setAttribute("teams", teams);
        } catch (SQLException e) {
            session.setAttribute("error", "Failed to fetch teams");

        }
        request.getRequestDispatcher("/WEB-INF/views/teams.jsp").forward(request, response);
    }
}
