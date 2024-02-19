package servlets.api.users;

import servlets.pojo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/myInfo")
public class MyInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().println("This servlet contains your session information.");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if(user == null){
            response.getWriter().println("Please enter any info about yourself first");
            return;
        }
        response.getWriter().println(session.getAttribute("userInfo"));
    }
}
