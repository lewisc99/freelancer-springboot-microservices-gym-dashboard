package com.lewis.msauthentication.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lewis.msauthentication.config.util.jwtUtil;
import com.lewis.msauthentication.entities.dtos.TokenResponseDTO;
import com.lewis.msauthentication.entities.models.LoginModel;
import com.lewis.msauthentication.config.SecurityConstants;
import com.lewis.msauthentication.services.MyEmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyEmployeeDetailsService userDetailsService;


    @PostMapping(value="login")
    public ResponseEntity<TokenResponseDTO> logIn(@RequestBody LoginModel login) throws Exception
    {
        try
        {
            authenticate(login);
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password",e);
        }

        TokenResponseDTO tokenResponseDTO =  generateToken(login);
        return ResponseEntity.ok(tokenResponseDTO);
    }

    private TokenResponseDTO generateToken(LoginModel login) {
        List<String> getRoles = new ArrayList<>();
        Collection<? extends GrantedAuthority> roles = userDetailsService.loadUserByUsername(login.getEmail()).getAuthorities();

        for (GrantedAuthority role: roles)
        {
            String newRole = role.getAuthority();
            getRoles.add(newRole);
        }
        System.out.println(roles);
        String token =  jwtUtil.generateToken(login.getEmail(), getRoles);
        TokenResponseDTO  tokenResponse = new TokenResponseDTO();
        tokenResponse.setToken(token);
        tokenResponse.setCreated(new Date());
        return tokenResponse;
    }

    private void authenticate(LoginModel login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(), login.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
