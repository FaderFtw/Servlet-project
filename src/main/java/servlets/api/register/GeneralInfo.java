package servlets.api.register;

import servlets.pojo.User;
import servlets.utils.AssertValue;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/registerGeneralInfo")
public class GeneralInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        if( !AssertValue.valueExistsNotNull(firstName) && !AssertValue.valueExistsNotNull(lastName) ){
            response.getWriter().println("Please enter first and last name");
            return;
        }
        HttpSession session=request.getSession();
        User user = new User(firstName, lastName);
        session.setAttribute("userInfo", user);
        Cookie cookie = new Cookie( "user", URLEncoder.encode( user.toString(), "UTF-8" ) );
        response.addCookie(cookie);
        response.sendRedirect("personalInfoForm");
    }
}
