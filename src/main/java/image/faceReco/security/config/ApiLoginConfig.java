package image.faceReco.security.config;

import image.faceReco.security.filter.ApiUsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public final class ApiLoginConfig<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, ApiLoginConfig<H>, ApiUsernamePasswordAuthenticationFilter> {
    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;
    private AuthenticationManager authenticationManager;

    public ApiLoginConfig(){
        super(new ApiUsernamePasswordAuthenticationFilter(), null);
    }

    @Override
    public void init(H http) throws Exception {
        super.init(http);
    }

   @Override
   public void configure(H http){
       if(authenticationManager == null){
           authenticationManager = http.getSharedObject(AuthenticationManager.class);
       }
       getAuthenticationFilter().setAuthenticationManager(authenticationManager);
       getAuthenticationFilter().setAuthenticationSuccessHandler(successHandler);
       getAuthenticationFilter().setAuthenticationFailureHandler(failureHandler);

       SessionAuthenticationStrategy sessionAuthenticationStrategy = http
               .getSharedObject(SessionAuthenticationStrategy.class);
       if (sessionAuthenticationStrategy != null) {
           getAuthenticationFilter().setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
       }
       RememberMeServices rememberMeServices = http
               .getSharedObject(RememberMeServices.class);
       if (rememberMeServices != null) {
           getAuthenticationFilter().setRememberMeServices(rememberMeServices);
       }
       http.setSharedObject(ApiUsernamePasswordAuthenticationFilter.class,getAuthenticationFilter());
       http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

   }

    public ApiLoginConfig<H> successHandlerApi(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    public ApiLoginConfig<H> failureHandlerApi(AuthenticationFailureHandler authenticationFailureHandler) {
        this.failureHandler = authenticationFailureHandler;
        return this;
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

}
