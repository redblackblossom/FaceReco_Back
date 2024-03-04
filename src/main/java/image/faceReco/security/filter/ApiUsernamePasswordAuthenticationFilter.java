package image.faceReco.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import image.faceReco.domain.DTO.LoginDTO;
import image.faceReco.security.token.ApiUsernamePasswordAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class ApiUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper mapper = new ObjectMapper();

    public ApiUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if(!HttpMethod.POST.name().equals(request.getMethod())){
            throw new IllegalArgumentException("Authentication method not supported");
        }

        LoginDTO loginDTO = mapper.readValue(request.getReader(), LoginDTO.class);

        if(!StringUtils.hasText(loginDTO.getUserId()) || !StringUtils.hasText(loginDTO.getPwd())){
            throw new AuthenticationServiceException("Id or Password is empty");
        }

        ApiUsernamePasswordAuthenticationToken token = new ApiUsernamePasswordAuthenticationToken(loginDTO.getUserId(), loginDTO.getPwd());
        return this.getAuthenticationManager().authenticate(token);
    }
}


