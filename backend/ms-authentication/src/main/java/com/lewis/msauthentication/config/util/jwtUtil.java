package com.lewis.msauthentication.config.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.lewis.msauthentication.config.SecurityConstants;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class jwtUtil {

    public static String generateToken(String email, List<String> roles) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("UserDetails")
                .withClaim("email", email)
                .withClaim("roles", roles)
                .withIssuedAt(new Date())
                .withIssuer("lewis.com")

                .sign(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()));
    }
}
