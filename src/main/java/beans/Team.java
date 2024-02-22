package beans;

import java.io.Serializable;

public class Team implements Serializable {
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
}

