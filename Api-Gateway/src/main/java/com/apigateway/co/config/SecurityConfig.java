//package com.apigateway.co.config;
//
//import com.apigateway.co.filter.AuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//
//@Configuration
//public class SecurityConfig {
//
////    @Autowired
////    private AuthenticationFilter filter;
////
////
////    @Bean
////    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
////        return http
////                .csrf(ServerHttpSecurity.CsrfSpec::disable)  // Disable CSRF protection
////                .cors(cors -> cors.disable())  // Disable CORS if not needed
////                .authorizeExchange(authorizeExchangeSpec ->
////                                authorizeExchangeSpec
////                                        .pathMatchers("/home/**").authenticated()  // Require authentication for /home/** endpoints
////                                        .pathMatchers("/auth/register", "/auth/token", "/eureka").permitAll() // Allow public access to these endpoints
////
//////                                .pathMatchers("/auth/**").permitAll()  // Allow public access to /auth/** endpoints
////                                        .anyExchange().authenticated()  // Require authentication for all other endpoints
////                )
////                .build();
//////
////    @Bean
////    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
////        return http
////                .csrf(ServerHttpSecurity.CsrfSpec::disable)  // Disable CSRF protection
////                .cors(cors -> cors.disable())  // Disable CORS if not needed
////                .authorizeExchange(authorizeExchangeSpec ->
////                        authorizeExchangeSpec
////                                .pathMatchers("/home/**").authenticated()  // Require authentication for /home/** endpoints
////                                .pathMatchers("/auth/**").permitAll()  // Allow public access to /auth/** endpoints
////                                .anyExchange().authenticated()  // Require authentication for all other endpoints
////                )
////                .addFilterBefore((WebFilter) filter, SecurityWebFiltersOrder.AUTHENTICATION)  // Add custom filter before default authentication filter
////                .build();
//
////    @Bean
////    public SecurityWebFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
////        return http
////                .csrf(c  -> c.disable() )  // Disable CSRF protection
////                .cors(cors -> cors.disable())  // Disable CORS if not required
////                .authorizeHttpRequests(
////                        auth ->
////                                auth
////                                .requestMatchers("/home/**").authenticated()  // Require authentication for /home/** endpoints
////                                .requestMatchers("/auth/**").permitAll()  // Allow public access to /auth/** endpoints
////                                .anyRequest().authenticated()  // Require authentication for all other endpoints
////                ).addFilterBefore(filter, WebFilter.class)  // Add custom filter before default authentication filter
////                .build();
////    }
////
////    @Bean
////    public SecurityWebFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
////        return http.csrf(csrf -> csrf.disable())
////                .cors(cors -> cors.disable()).
////                authorizeHttpRequests(
////                        auth ->
////                                auth.requestMatchers("/home/**")
//////                                        .hasRole("ADMIN")
////                                        .authenticated()
////                                        .requestMatchers("/auth/**").permitAll()
////                                        .anyRequest().authenticated())
//////                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
//
//
////@Configuration
////public class SecurityConfig {
//////
//////    @Bean
//////    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//////        http
//////                .csrf().disable()  // Disable CSRF protection
//////                .authorizeExchange()
//////                .pathMatchers("/auth/**").permitAll()  // Allow unauthenticated access to /auth/** endpoints
//////                .anyExchange().authenticated()  // Require authentication for other endpoints
//////                .and()
//////                .formLogin().disable()  // Disable form login
//////                .httpBasic().disable(); // Disable HTTP basic authentication if not needed
//////        return http.build();
//////    }
//////}
//    }
//}