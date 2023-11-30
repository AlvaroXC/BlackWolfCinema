package com.uady.blackWolfCinema.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uady.blackWolfCinema.service.UserService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class DemoSecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler ) throws Exception{
        
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("img/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->
                                form
                                .loginPage("/loginPage")
                                .loginProcessingUrl("/authenticateTheUser")

                                        .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                ).logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));

        
        return http.build();
    }
}
