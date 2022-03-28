package com.example.remindme.controller.web.security;

import com.example.remindme.controller.web.service.UserEntityService;
import com.example.remindme.classes.persistence.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserEntityService UserEntityService; //j'ai enlever le final

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        if(UserEntityService.users().size()==0)
            System.out.println("No users");

        for (UserEntity elem : UserEntityService.users()) {
            // System.out.println(elem.getName());
            auth.inMemoryAuthentication()
                .withUser(elem.getName()).password(elem.getPassword()).roles("ADMIN");
        }
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login-form")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/login-form"); //rediriger ailleur plus tard
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}