package image.faceReco.config;

import image.faceReco.filter.ApiUsernamePasswordAuthenticationFilter;
import image.faceReco.filter.RequestValidationBeforeFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig  {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement((sessionManagement) ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer->corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                //.addFilterAt(new ApiUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf((csrf)->csrf.disable())
            .authorizeHttpRequests((requests)->{
            //requests.requestMatchers("/").authenticated();
            requests.requestMatchers("/register", "/login", "/").permitAll();});

        //http.formLogin(Customizer.withDefaults());
        //http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public ApiUsernamePasswordAuthenticationFilter apiUsernamePasswordAuthenticationFilter(){
        ApiUsernamePasswordAuthenticationFilter filter = new ApiUsernamePasswordAuthenticationFilter("/login");
        filter.setAuthenticationManager(customerAuthenticationManager());
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        return filter;
    }

    @Bean
    public CustomerAuthenticationManager customerAuthenticationManager(){
        return new CustomerAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
