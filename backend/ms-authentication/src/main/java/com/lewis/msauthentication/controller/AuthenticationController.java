package com.lewis.msauthentication.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lewis.msauthentication.entities.models.LoginModel;
import com.lewis.msauthentication.filters.SecurityConstants;
import com.lewis.msauthentication.services.MyEmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("v1/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyEmployeeDetailsService userDetailsService;


    @PostMapping(value="login")
    public ResponseEntity<String> logIn(@RequestBody LoginModel login) throws Exception
    {
        try
        {
            authenticate(login);
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password",e);
        }

        String token = generateToken(login);

        return ResponseEntity.ok().body(token);
    }

    private void authenticate(LoginModel login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(), login.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    private static String generateToken(LoginModel login) {
        String token = JWT.create()
                .withSubject(login.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        return token;
    }

    @PostMapping(value="logout")
    public ResponseEntity<Void> logout()
    {
        if(SecurityContextHolder.getContext().getAuthentication()
                .isAuthenticated())
        {
            SecurityContextHolder.getContext().getAuthentication()
                    .setAuthenticated(false);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.internalServerError().build();
        }
    }
}
