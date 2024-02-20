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
            String name = request.getParameter("name");
            String country = request.getParameter("country");
            String league = request.getParameter("league");
            int numTitles = Integer.parseInt(request.getParameter("numTitles"));
            int numPlayers = Integer.parseInt(request.getParameter("numPlayers"));
            String stadium = request.getParameter("stadium");





            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO teams (name, country, league, nb_titles, nb_players, stadium) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, league);
            preparedStatement.setInt(4, numTitles);
            preparedStatement.setInt(5, numPlayers);
            preparedStatement.setString(6, stadium);

            int rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                session.setAttribute("success","Team Added");
                response.sendRedirect("/ListTeamsServlet");
            } else {
                session.setAttribute("error","Team Not Added");
                response.sendRedirect("/ListTeamsServlet");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error","Invalid number format");
            response.sendRedirect("/ListTeamsServlet");
        } catch (SQLException e) {
            session.setAttribute("error","Team Not Added");
            response.sendRedirect("/ListTeamsServlet");
        }
    }
}
