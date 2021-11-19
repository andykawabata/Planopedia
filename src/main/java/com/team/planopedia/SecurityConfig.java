/**
 * Class to configure security of app
 */
package com.team.planopedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    
    /**
     * this method configures the authentication behavior of app
     * @param http
     * @throws Exception 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                //.antMatchers("/user").hasRole("USER") //ADD RESTRICTED ROUTES HERE
                .antMatchers("/").permitAll()
                .and()
            .oauth2Login()
                .loginPage("/auth/login-page")
                .defaultSuccessUrl("/auth/success", true)
                .and()
            .logout()
                .logoutSuccessHandler(oidcLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .and()
            .csrf().disable();
        

    }
    /**
     * Callback function to handle login behavior
     * @return 
     */
    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("/");
    return successHandler;
}
}
