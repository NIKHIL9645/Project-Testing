package com.apigateway.co.filter;

//    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//running perfectly - code

import com.apigateway.co.exception.CustomException;
import com.apigateway.co.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>  {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("000000000000000000000000000000000000");
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new CustomException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);

                    try {
                        System.out.println("111111111111111111111111111111111111111");
                        System.out.println(token);
                        jwtUtil.validateToken(token);
                        Claims claims = jwtUtil.getAllClaims(token);
                        Set<String> roles = jwtUtil.extractRoles(token);
                        System.out.println("rolessssssssssssssssssssssssssssssssssssssssssssssss");
                        System.out.println(roles);

                        if (roles.isEmpty() || !hasRequiredRole(roles, config.getRole())) {
                            throw new CustomException("Insufficient role");
                        }
                    } catch (ExpiredJwtException e) {
                        throw new CustomException("JWT Token has expired: " + e.getMessage());
                    } catch (MalformedJwtException e) {
                        throw new CustomException("JWT Token is malformed: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CustomException("JWT Token validation error: " + e.getMessage());
                    }
                } else {
                    throw new CustomException("Invalid Authorization header format");
                }
            }
            return chain.filter(exchange);
        };
    }

    private boolean hasRequiredRole(Set<String> userRoles, Set<String> requiredRoles) {
        return requiredRoles.stream().anyMatch(userRoles::contains);
    }

    public static class Config {
        private Set<String> role;

        public Set<String> getRole() {
            return role;
        }

        public void setRole(Set<String> role) {
            this.role = role;
        }
    }
}

//    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
//    @Autowired
//    private RouteValidator validator;
//    @Autowired
//    private JwtUtil jwtHelper;
//
//
//    @Autowired
//    private RestTemplate template;
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new CustomException("Missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//                System.out.println(authHeader);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    String token = authHeader.substring(7);
//                    try {
//                        System.out.println(authHeader);
//                        jwtHelper.validateToken(token);
//                        Claims c = jwtHelper.GetAllClaims(token);
//                        System.out.println(c);
//                        Object rolesObject =c.get("roles");
//                        List<String> roles = new ArrayList<>();
//
//                        List<Map<String, String>> authorities  = (List<Map<String, String>>) rolesObject;
//                        System.out.println("555555555");
//                        System.out.println(authorities.getFirst().get("authority"));
//                        roles.add(authorities.getFirst().get("authority"));
//
//                        System.out.println("worllkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//                        System.out.println(roles);
//                        System.out.println("11111111111111111111111111111111");
//                        System.out.println("getting role");
//                        System.out.println(config.getRole());
//                        System.out.println("2222222222222222222222222222222222222");
//                        System.out.println(c.getAudience());
//                        System.out.println("333333333333333333333333333333333333333");
//                        if (roles == null || !hasRequiredRole(roles,config.getRole() )) {
//                            throw new CustomException("Insufficient role");
//                        }
////                        Jws<Claims> o =  jwtHelper.getClaimFromToken(token);
////                        System.out.println(o.getSignature());
//                    } catch (ExpiredJwtException e) {
//                        System.out.println("JWT Token has expired: " + e.getMessage());
//                        throw new CustomException("JWT Token has expired: " + e.getMessage());
//                    } catch (MalformedJwtException e) {
//                        throw new CustomException("JWT Token is malformed: " + e.getMessage());
//                    } catch (Exception e) {
//                        System.out.println("JWT Token validation error: " + e.getMessage());
//
//                        throw new CustomException("Unauthorized access to application  "+e.getMessage());
//                    }
//                } else {
//                    System.out.println("Invalid Authorization header format");
//                    throw new CustomException("Invalid Authorization header format");
//                }
//
//
//
//            }
//
//
//            return chain.filter(exchange);
//        };
//    }
//    private boolean hasRequiredRole(List<String> userRoles ,Set<String> requiredRoles){
//        for (String role : requiredRoles) {
//            if (userRoles.contains(role)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//
//    public static class Config {
//
//
//
//        private Set<String> role;
//
//        public Set<String>  getRole() {
//            return role;
//        }
//
//        public void setRole(Set<String>  role) {
//            this.role = role;
//        }
//    }
//}


//
//import com.apigateway.co.exception.CustomException;
//import com.apigateway.co.util.JwtUtil;
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    @Autowired
//    private RouteValidator validator;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new CustomException("Missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//
//                try {
//                    jwtUtil.validateToken(authHeader);
//                    Claims claims = jwtUtil.GetAllClaims(authHeader);
//
//                    List<Map<String, String>> roles = (List<Map<String, String>>) claims.get("roles");
//                    System.out.println(roles);
//
//                    System.out.println("work1111111111111111111111111111111111111111111111111");
//                    String httpMethod = exchange.getRequest().getMethod().toString();
//
//                    System.out.println(httpMethod);
//
//                    String requestPath = exchange.getRequest().getURI().getPath();
//                    System.out.println(requestPath);
//
//
//                    System.out.println("work11111111112222222222222222222222222222222222222222");
//
//                    if (roles != null && !roles.isEmpty()) {
//                        Map<String, String> roleMap = roles.get(0);
//                        String authority = roleMap.get("authority");
//                        System.out.println("55555555555");
//                        System.out.println(authority);
//                    System.out.println("work3");
//
//
//                        if (!isAuthorized(authority, requestPath, httpMethod)) {
//                            throw new CustomException("Unauthorized access to application");
//                        }
//                    } else {
//                        throw new CustomException("Roles not found in token");
//                    }
//                } catch (Exception e) {
//                    throw new CustomException("Unauthorized access to application");
//                }
//            }
//            return chain.filter(exchange);
//        };
//    }
//
//    private boolean isAuthorized(String authority, String requestPath, String httpMethod) {
//
//
////        Map<String, List<String>> accessControlRules = Map.of(
////                "POST", List.of("ROLE_ADMIN"),  // POST requests require ROLE_ADMIN
////                "GET", List.of("ROLE_USER"), // GET requests require ROLE_USER or ROLE_ADMIN
////                "PUT", List.of("ROLE_ADMIN"),  // PUT requests require ROLE_ADMIN
////                "DELETE", List.of("ROLE_ADMIN") // DELETE requests require ROLE_ADMIN
////        );
//
//        Map<String, List<String>> accessControlRules = Map.of(
//                "/projectservice:POST", List.of("ROLE_ADMIN"),  // Only admins can POST to /projectservice
//                "/projectservice/\\d+:GET", List.of("ROLE_USER"), // Users and admins can GET from /projectservice/{id}
//                "/projectservice/\\d+:PUT", List.of("ROLE_ADMIN"), // Only admins can PUT to /projectservice/{id}
//                "/projectservice/\\d+:DELETE", List.of("ROLE_ADMIN") // Only admins can DELETE from /projectservice/{id}
//        );
//
//
//        // Check if the request path + method has specific access rules
//        String key = requestPath + ":" + httpMethod;
//        System.out.println("Key" +key);
//        List<String> requiredRoles =
//                accessControlRules.get(key);
//        System.out.println(accessControlRules);
//
//        System.out.println("requiredRoles"+requiredRoles);
//        if (requiredRoles != null) {
//            return requiredRoles.contains(authority);
//        }
//
//        return false; // Default to false if no matching path or role found
//    }
//
//    public static class Config {
//    }
//}




//post api works here but not get
//
//import com.apigateway.co.exception.CustomException;
//import com.apigateway.co.util.JwtUtil;
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    @Autowired
//    private RouteValidator validator;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            System.out.println("aaaaaaaaaaaaaaaaaa");
//            if (validator.isSecured.test(exchange.getRequest())) {
//                System.out.println("bbbbbbbbbbbb");
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//
//                    throw new CustomException("missing authorization header");
//
//                }
//                System.out.println("ccccccccccccc");
//                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                System.out.println(authHeader);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//                try {
//                    System.out.println("88888888888888");
//                    jwtUtil.validateToken(authHeader);
//
//                    System.out.println("111111111111111111111111111111111111111111111111");
//
//                    System.out.println(jwtUtil.GetAllClaims(authHeader));
//                    Claims claims = jwtUtil.GetAllClaims(authHeader);
//
//
//
//                    List<Map<String, String>> roles = (List<Map<String, String>>) claims.get("roles");
//
//
//                    if (roles != null && !roles.isEmpty()) {
//                        // Assuming roles list has only one role for simplicity
//                        Map<String, String> roleMap = roles.get(0);
//                        System.out.println(roleMap);
//                        String s = roleMap.get("authority");
//                        System.out.println(s);
//
//                        // Extract values from the map using a stream
////                        Stream<String> stringStream = roleMap.values().stream();
////                        stringStream.forEach(System.out::println);
////
//
//                        // Extract HTTP method
//                        String httpMethod = exchange.getRequest().getMethodValue();
//
//                        // Enforce authorization based on method and role
//                        if (!isAuthorized(s, httpMethod)) {
//                            throw new CustomException("unauthorized access to application");
//                        }
//                    }
//
//
////                    List roles = (List) claims.get("roles");
////                    Map<String,String> map = (Map<String, String>) roles;
////                    System.out.println(map.get("authority"));
////
//
//                } catch (Exception e) {
//                    System.out.println("invalid access...!");
//                    throw new CustomException("unauthorized access to application");
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }
//
//    private boolean isAuthorized(String authority, String httpMethod) {
//        // Define method-based access rules
//        Map<String, List<String>> methodAccessRules = Map.of(
//                "POST", List.of("ROLE_ADMIN"),    // Only admins can POST
//                "GET", List.of("ROLE_USER", "ROLE_ADMIN") // Users and admins can GET
//                // Add more rules as needed
//        );
//
//        List<String> allowedRoles = methodAccessRules.get(httpMethod);
//
//        if (allowedRoles != null) {
//            // Check if the user's role is in the list of allowed roles for the method
//            return allowedRoles.contains(authority);
//        }
//
//        // Default to false if no matching method or role found
//        return false;
//    }
//
//    public static class Config {
//
//    }
//
//
//
////
////    private boolean isAuthorized(String authority, String requestPath, String httpMethod) {
////        // Define access control rules
////        // Example rules: a map where the key is the path + method and the value is a list of required roles
////        Map<String, List<String>> accessControlRules = Map.of(
////                "/projectservice/**:POST", List.of("ROLE_ADMIN"),     // Only admins can POST to /api/data/submit
//////                "/projectservice:GET", List.of("ROLE_USER", "ROLE_ADMIN"), // Users and admins can GET from /api/data/view
////                "/projectservice:GET", List.of("ROLE_USER"), // Users and admins can GET from /api/data/view
////                "/api/admin/dashboard:GET", List.of("ROLE_ADMIN") // Only admins can GET /api/admin/dashboard
////                // Add more rules as needed
////        );
////
////        // Check if the request path + method has specific access rules
////        String key = requestPath + ":" + httpMethod;  // Construct key for the map
////        List<String> requiredRoles = accessControlRules.get(key);
////
////        if (requiredRoles != null) {
////            // Check if the user's role is in the list of required roles
////            return requiredRoles.contains(authority);
////        }
////
////        // Default to false if no matching path or role found
////        return false;
////    }
//}
*/