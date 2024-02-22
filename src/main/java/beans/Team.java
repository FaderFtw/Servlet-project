package beans;

import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Team implements Serializable {
    private Integer id;
    private String name;
    private String country;
    private String league;
    private int numberOfTitles;
    private int numberOfPlayers;
    private String stadium;

    private String status = "";

    public Team() {
    }

    public Team(String name, String country, String league, int numberOfTitles, int numberOfPlayers, String stadium) {
        this.name = name;
        this.id = null;
        this.country = country;
        this.league = league;
        this.numberOfTitles = numberOfTitles;
        this.numberOfPlayers = numberOfPlayers;
        this.stadium = stadium;
    }

    public Team(Integer id, String name, String country, String league, int numberOfTitles, int numberOfPlayers, String stadium) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.league = league;
        this.numberOfTitles = numberOfTitles;
        this.numberOfPlayers = numberOfPlayers;
        this.stadium = stadium;
    }

    // Getters and setters for name, country, league, numberOfTitles, numberOfPlayers, and stadium
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getNumberOfTitles() {
        return numberOfTitles;
    }

    public void setNumberOfTitles(int numberOfTitles) {
        this.numberOfTitles = numberOfTitles;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static void all(Connection connection, HttpServletRequest request, List<Team> teams) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teams")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String league = resultSet.getString("league");
                int numTitles = resultSet.getInt("nb_titles");
                int numPlayers = resultSet.getInt("nb_players");
                String stadium = resultSet.getString("stadium");

                Team team = new Team(id, name, country, league, numTitles, numPlayers, stadium);
                teams.add(team);
            }
        }
    }

    public static boolean addTeam(Connection connection, HttpServletRequest request) throws SQLException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String league = request.getParameter("league");
        int numTitles = Integer.parseInt(request.getParameter("numTitles"));
        int numPlayers = Integer.parseInt(request.getParameter("numPlayers"));
        String stadium = request.getParameter("stadium");
        Team team = new Team(name, country, league, numTitles, numPlayers, stadium);

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO teams (name, country, league, nb_titles, nb_players, stadium) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getCountry());
            preparedStatement.setString(3, team.getLeague());
            preparedStatement.setInt(4, team.getNumberOfTitles());
            preparedStatement.setInt(5, team.getNumberOfPlayers());
            preparedStatement.setString(6, team.getStadium());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static boolean deleteTeam(Connection connection, HttpServletRequest request) throws SQLException {
        String teamId = request.getParameter("teamId");
        if (teamId != null && !teamId.isEmpty()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM teams WHERE id = ?")) {
                preparedStatement.setInt(1, Integer.parseInt(teamId));
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        }
        else {
            return false;
        }
    }
}

