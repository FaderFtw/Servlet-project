package filters;

import beans.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = { "/Teams"}, filterName = "AuthFilter")
public class AuthorizedFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;

    public AuthorizedFilter() {
        super();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null){
            User user = (User) session.getAttribute("user");
            if (user == null ) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/Auth?action=viewLogin");
            } else {
                request.setAttribute("id", user.getId());
                chain.doFilter(request, response);
            }
        }
        else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Auth?action=viewLogin");
        }


    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
