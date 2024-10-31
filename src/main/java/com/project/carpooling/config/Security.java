package com.project.carpooling.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security
{
    @Autowired
    userDetailsService UserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(Customizer->Customizer.disable());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/password/reset","/password/change","/email/verify","/email/post","/signup", "/login","/save","/").permitAll() // Allow public access to /signup and /login
                .anyRequest().authenticated() // Require authentication for all other URLs
        );
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider obj=new DaoAuthenticationProvider();
        obj.setPasswordEncoder(new BCryptPasswordEncoder());
        obj.setUserDetailsService(UserDetailsService);

        return obj;
    }
}
