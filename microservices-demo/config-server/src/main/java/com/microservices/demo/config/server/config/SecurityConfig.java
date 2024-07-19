package com.microservices.demo.config.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/actuator/**").permitAll()
                                .requestMatchers("/encrypt/**").permitAll()
                                .requestMatchers("/decrypt/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRequestHandler((CsrfTokenRequestHandler) new ServerCsrfTokenRequestAttributeHandler())
                ); // Disable CSRF if not required, but ensure to understand the implications.

        return http.build();
    }
}
