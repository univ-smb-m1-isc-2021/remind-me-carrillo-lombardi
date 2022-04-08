package com.example.remindme.config;

import com.example.remindme.service.UserEntityService;
import com.example.remindme.classes.persistence.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final UserEntityService userEntityService;
	
    // public SecurityConfig(UserEntityService userEntityService) {
    //     this.userEntityService = userEntityService;
    // }

    @Autowired
    UserEntityService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String email = (String) authentication.getPrincipal();
                String providedPassword = (String) authentication.getCredentials();
                UserEntity user = userService.findAndAuthenticateUser(email, providedPassword);
                System.out.println(":::::");
                if (user == null) {
                    System.out.println("oh no");
                    throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
                }
                System.out.println("Yes !");
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/admin", true)
            .permitAll()
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/register").permitAll()
            .antMatchers("/register/**").permitAll()
            .anyRequest().authenticated();
    }

    // @Override
    // protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    //     // System.out.println("\nDans connexion\n\n");
    //     // for (UserEntity elem : userEntityService.users()) {
    //     //     System.out.println(elem.getName());
    //     // } //!pk ça fonctionne plus ?

    //     for (UserEntity elem : userEntityService.users()) {
    //         auth.inMemoryAuthentication()
    //             .withUser(elem.getName()).password(elem.getPassword()).roles("ADMIN");
    //     }
    // }

    // @Override
    // protected void configure(final HttpSecurity http) throws Exception {
    //     http.csrf().disable()
    //             .authorizeRequests()
    //             .antMatchers("/admin/**").hasRole("ADMIN")
    //             .anyRequest().permitAll()
    //             .and()
    //             .formLogin()
    //             .loginPage("/login")
    //             .loginProcessingUrl("/perform_login")
    //             .defaultSuccessUrl("/admin", true)
    //             .failureUrl("/login");
    // }
}