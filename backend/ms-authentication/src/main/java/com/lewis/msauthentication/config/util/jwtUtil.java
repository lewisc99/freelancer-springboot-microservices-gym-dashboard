package com.lewis.msauthentication.config.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.lewis.msauthentication.config.SecurityConstants;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class jwtUtil {

    public static String generateToken(String email, List<String> roles) throws IllegalArgumentException, JWTCreationException {

        Date issuedAt = new Date();
        Date expiresAt = new Date();
        LocalDateTime localTime = LocalDateTime.ofInstant(expiresAt.toInstant(), ZoneId.systemDefault());
        LocalDateTime calculateExpiresAt = localTime.plusMinutes(5);
        expiresAt = Date.from(calculateExpiresAt.atZone(ZoneId.systemDefault()).toInstant());

        return JWT.create()
                .withSubject("UserDetails")
                .withClaim("email", email)
                .withClaim("roles", roles)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withIssuer("lewis.com")
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()));
    }
}
