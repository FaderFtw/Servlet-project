package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/Teams")
public class TeamControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call the appropriate method based on the request parameters or URL
        String action = request.getParameter("action");
        if ("viewTeams".equals(action)) {
            viewTeams(request, response);
        } else if ("viewTeamForm".equals(action)) {
            viewTeamForm(request, response);
        } else {
            viewTeams(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call the appropriate method based on the request parameters or URL
        String action = request.getParameter("action");
        if ("addTeam".equals(action)) {
            addTeam(request, response);
        } else if ("deleteTeam".equals(action)) {
            deleteTeam(request, response);
        } else {
            viewTeams(request, response);
        }
    }

    private void viewTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/ListTeamsServlet").forward(request, response);
    }

    private void viewTeamForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/teamForm.jsp").forward(request, response);
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/AddTeamServlet").forward(request, response);
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/DeleteTeamServlet").forward(request, response);
    }
}
