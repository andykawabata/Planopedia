package com.team.planopedia;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/user").hasRole("USER") //ADD RESTRICTED ROUTES HERE
                .antMatchers("/").permitAll()
                .and()
          .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/loginSucess", true);
    }
}
