package image.faceReco.security.provider;

import image.faceReco.domain.Customer;
import image.faceReco.repository.CustomerRepository;
import image.faceReco.security.token.ApiUsernamePasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customer = customerRepository.findByUserId(userId);
        if(!customer.isEmpty()){
            if(passwordEncoder.matches(pwd, customer.get(0).getPwd())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
                return new ApiUsernamePasswordAuthenticationToken(userId, pwd,authorities);
            }else{
                throw new BadCredentialsException("Invalid password");
            }
        }else{
            throw new BadCredentialsException("User not found by userId");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ApiUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
