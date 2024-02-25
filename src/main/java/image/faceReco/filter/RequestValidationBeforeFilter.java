package image.faceReco.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;

public class RequestValidationBeforeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = req.getParameter("Username");

        if(username.contains("@")){
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        chain.doFilter(request,response);
    }
}
