package servlets.api.register;

import servlets.pojo.User;
import servlets.utils.AssertValue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/registerPersonalInfo")
public class PersonalInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session=request.getSession();
        if(session.getAttribute("userInfo") == null){
            response.getWriter().println("Please enter general info first");
            return;
        }
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        if( !AssertValue.valueExistsNotNull(email) && !AssertValue.valueExistsNotNull(phoneNumber) ){
            response.getWriter().println("Please enter email and phone number");
            return;
        }
        User user = (User) session.getAttribute("userInfo");
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        session.setAttribute("userInfo", user);
        Cookie cookie = new Cookie( "user", URLEncoder.encode( user.toString(), "UTF-8" ) );
        response.addCookie(cookie);
        response.sendRedirect("/myInfo");
    }
}
