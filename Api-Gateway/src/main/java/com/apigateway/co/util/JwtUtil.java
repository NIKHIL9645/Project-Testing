package com.apigateway.co.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {



    private static final String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    // Get all claims from the token
    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate the token
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token: " + e.getMessage());
        }
    }

    // Extract roles from claims
    @SuppressWarnings("unchecked")
    public Set<String> extractRoles(String token) {
        Claims claims = getAllClaims(token);
        // Assuming roles are stored as a list of maps
        List<Map<String, String>> rolesList = (List<Map<String, String>>) claims.get("roles");
        return rolesList.stream()
                .map(roleMap -> roleMap.get("authority"))
                .collect(Collectors.toSet());
    }

    // Utility method to get the signing key
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public static final String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
//
//
//
//    public Claims GetAllClaims(final String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(SECRET)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims;
//    }
//
//
//
//
//    public void validateToken(final String token) {
//        System.out.println("1111111111111111111111111111111111111111111");
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//
//        System.out.println("11111111111111111111111111111111111111111111");
//    }
//
//
//

//
//


//    public Claims validateTokengetClaims(final String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        System.out.println("----------------********************************--------------");
//        System.out.println("Claims:");
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Expiration: " + claims.getExpiration());
//        System.out.println("Issued At: " + claims.getIssuedAt());
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Roles: " + claims.get("roles"));        System.out.println("----------------********************************--------------");
//        return claims;
//    }
//



//        private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}