package com.jwt.example.config;

import com.jwt.example.security.JwtAuthenticationEntryPoint;
import com.jwt.example.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    //
    @Autowired
    public JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

//
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(csrf -> csrf.disable())
////                .cors(cors -> cors.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/home/**").hasAnyRole("ADMIN", "USER") // Restrict to ADMIN role
////                        .requestMatchers("/auth/**").permitAll()
////                        .requestMatchers("/projectservice/**").authenticated()
////                        .anyRequest().permitAll())
////                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////
////
//
//


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable()).
                authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/home/**")
//                                        .hasRole("ADMIN")
                                        .authenticated()
                                        .requestMatchers("/auth/**").permitAll()
                                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}