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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teams");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String league = resultSet.getString("league");
                int numTitles = resultSet.getInt("nb_titles");
                int numPlayers = resultSet.getInt("nb_players");
                String stadium = resultSet.getString("stadium");

                Team team = new Team(name, country, league, numTitles, numPlayers, stadium);
                teams.add(team);
            }

            session.setAttribute("teams", teams);
            request.getRequestDispatcher("/Teams").forward(request, response);
        } catch (SQLException e) {
            session.setAttribute("error", "Failed to fetch teams");
            response.sendRedirect("/Teams");
        }
    }
}
