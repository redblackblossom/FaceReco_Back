package image.faceReco.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtIssue {
    private final SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

    public void jwtAuthentication(HttpServletResponse response, Authentication authentication){
        Date now = new Date();
        String jwt = Jwts.builder().setIssuer("FaceReco").setSubject("JWT TOKEN")
                .claim("userId", authentication.getName())
                .claim("id", authentication.getDetails())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ 30000000))
                .signWith(key).compact();
        response.setHeader(SecurityConstants.JWT_Header, jwt);
    }
}
