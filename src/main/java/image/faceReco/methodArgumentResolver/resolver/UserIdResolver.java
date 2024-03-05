package image.faceReco.methodArgumentResolver.resolver;

import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static image.faceReco.security.jwt.SecurityConstants.JWT_Header;

@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) &&
                parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String jwt;
        Integer userId = null;
        jwt = request.getHeader(JWT_Header);
        if(jwt!=null){
            String[] splitString = jwt.split("\\.");
            String base64EncodeBody = splitString[1];
            String bodyString = new String(Base64.getDecoder().decode(base64EncodeBody), StandardCharsets.UTF_8);
            JSONObject jsonObj = new JSONObject(bodyString);
            userId = jsonObj.getInt("id");
        }
        else{
            throw new IllegalArgumentException("Request does not have Token");
        }
        return userId;
    }
}
