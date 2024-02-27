package image.faceReco.security.config;

import image.faceReco.security.Handler.ApiAuthenticationFailureHandler;
import image.faceReco.security.Handler.ApiAuthenticationSuccessHandler;
import image.faceReco.security.filter.ApiUsernamePasswordAuthenticationFilter;
import image.faceReco.security.filter.JwtTokenValidationFilter;
import image.faceReco.security.provider.ApiUsernamePasswordAuthenticationProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
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
                    requests.requestMatchers("/test").authenticated();
                    requests.requestMatchers("/register", "/login", "/").permitAll();});

        //http.formLogin(Customizer.withDefaults());
        //http.httpBasic(Customizer.withDefaults());
        http = apiConfigure(http);

        http.addFilterBefore(new JwtTokenValidationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private HttpSecurity apiConfigure(HttpSecurity http) throws Exception {
        http
                .apply(new ApiLoginConfig<>())
                .successHandlerApi(apiAuthenticationSuccessHandler())
                .failureHandlerApi(apiAuthenticationFailureHandler());
        return http;
    }
    @Bean
    public ApiAuthenticationSuccessHandler apiAuthenticationSuccessHandler(){
        return new ApiAuthenticationSuccessHandler();
    }
    @Bean
    public ApiAuthenticationFailureHandler apiAuthenticationFailureHandler(){
        return new ApiAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
