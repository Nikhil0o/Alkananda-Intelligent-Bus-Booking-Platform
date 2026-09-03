package com.example.alkananda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                // Enable CORS
                .cors(cors -> {})

                // Disable CSRF because we are using JWT
                .csrf(csrf -> csrf.disable())

                // Stateless session
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))

                // Authorization
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/user/add").permitAll()
                        .requestMatchers("/user/**").authenticated()

                        .requestMatchers("/route/get/all").permitAll()
                        .requestMatchers("/trip/get/all").permitAll()
                        .requestMatchers("/trip/search").permitAll()
                        .requestMatchers("/seat/**").permitAll()

                        .requestMatchers("/booking/**").authenticated()

                        .requestMatchers("/analytics/revenue-by-route").hasRole("ADMIN")
                        .requestMatchers("/analytics/booking-trends").hasRole("ADMIN")
                        .requestMatchers("/analytics/popular-routes").hasRole("ADMIN")
                        .requestMatchers("/analytics/**").hasRole("ADMIN")

                        .requestMatchers("/route/add").hasRole("ADMIN")
                        .requestMatchers("/bus/add").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                // JWT filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    // =========================
    // CORS CONFIGURATION
    // =========================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        // React frontend
        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        // HTTP methods
        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        // Headers
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Allow cookies / authorization headers
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}