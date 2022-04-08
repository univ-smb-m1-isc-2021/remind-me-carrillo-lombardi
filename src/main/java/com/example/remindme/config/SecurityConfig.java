package com.example.remindme.config;

import com.example.remindme.service.UserEntityService;
import com.example.remindme.classes.persistence.UserEntity;

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

    private final UserEntityService userEntityService;
	
    public SecurityConfig(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // System.out.println("\nDans connexion\n\n");
        // for (UserEntity elem : userEntityService.users()) {
        //     System.out.println(elem.getName());
        // } //!pk ça fonctionne plus ?

        for (UserEntity elem : userEntityService.users()) {
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
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/login"); //rediriger ailleur plus tard
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}