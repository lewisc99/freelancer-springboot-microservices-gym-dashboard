package com.lewis.msapigateway.config.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lewis.msapigateway.config.SecurityConstants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JWTUtil {

    public static Map<String, List<String>> validateTokenAndRetrieveSubject(String token) throws JWTVerificationException
    {
        Map<String, List<String>> claims = new HashMap<String, List<String>>();

        List<String> usernameList = new ArrayList<>();
        Claim roleClaim;

        JWTVerifier verifier =
                JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()))
                        .withIssuer("lewis.com")
                        .withSubject("UserDetails")
                        .build();

        DecodedJWT jwt = verifier.verify(token);

        usernameList.add(jwt.getClaim("email").asString());
        roleClaim = jwt.getClaim("roles");
        List<String> roleList = roleClaim.asList(String.class);

        claims.put("email",usernameList);
        claims.put("roles",roleList);

        return claims;
    }
}
