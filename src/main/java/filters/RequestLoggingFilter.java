package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            System.out.println(req.getRemoteAddr() + " | No cookies found");
            chain.doFilter(request, response);
            return;
        }
        Arrays.stream(cookies).filter(
                cookie -> cookie.getName().equals("user")
        ).forEach(cookie -> System.out.println(req.getRemoteAddr() + " | Client side Cookie:{"+cookie.getName()+","+cookie.getValue()+"}"));
        chain.doFilter(request, response);
    }
}
