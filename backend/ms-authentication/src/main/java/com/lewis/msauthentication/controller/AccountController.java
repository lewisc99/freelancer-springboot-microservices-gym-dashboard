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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("v1/account")
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

        final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
        String token = JWT.create()
                .withSubject(login.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        System.out.println("Token decode: ");
        System.out.println(JWT.decode(token));

        return ResponseEntity.ok().body(token);

    }
}
