package com.lewis.msauthentication.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lewis.msauthentication.entities.domain.Employee;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("v1/account/")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyEmployeeDetailsService userDetailsService;


    @PostMapping(value="login")
    public ResponseEntity<String> logIn(@RequestBody LoginModel login) throws Exception
    {
        try
        {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(), login.getPassword()
                    ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password",e);
        }

        String token = JWT.create()
                .withSubject(login.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        return ResponseEntity.ok().body(token);
    }

}
