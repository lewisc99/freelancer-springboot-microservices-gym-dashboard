package com.lewis.msauthentication.config.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.lewis.msauthentication.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class jwtUtil {

    @Autowired
    private  SecurityProperties securityProperties;

    public  String generateToken(String email, List<String> roles) throws IllegalArgumentException, JWTCreationException {

        Date issuedAt = new Date();
        Date expiresAt = new Date();
        LocalDateTime localTime = LocalDateTime.ofInstant(expiresAt.toInstant(), ZoneId.systemDefault());
        LocalDateTime calculateExpiresAt = localTime.plusMinutes(securityProperties.getExpiration());
        expiresAt = Date.from(calculateExpiresAt.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(securityProperties.getKey());
        System.out.println(securityProperties.getExpiration());

        return JWT.create()
                .withSubject("UserDetails")
                .withClaim("email", email)
                .withClaim("roles", roles)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withIssuer("lewis.com")
                .sign(Algorithm.HMAC256(securityProperties.getKey()));
    }
}
