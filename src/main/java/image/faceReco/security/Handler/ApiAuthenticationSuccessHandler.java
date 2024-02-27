package image.faceReco.security.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import image.faceReco.security.jwt.JwtIssue;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class ApiAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
   //private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        JwtIssue jwtIssue = new JwtIssue();
        jwtIssue.jwtAuthentication(response,authentication);

    }
}
