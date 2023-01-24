package com.lewis.msauthentication.controller;

import com.lewis.msauthentication.config.util.jwtUtil;
import com.lewis.msauthentication.entities.dtos.TokenResponseDTO;
import com.lewis.msauthentication.entities.models.LoginModel;
import com.lewis.msauthentication.services.MyEmployeeDetailsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyEmployeeDetailsService userDetailsService;




    @ApiOperation(value="Login Employees in the dashboard",
            notes = "it's only avaiable to use Employee credentials",
            response = TokenResponseDTO.class, code = 200
    )
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
        String token =  jwtUtil.generateToken(login.getEmail(), getRoles);
        TokenResponseDTO  tokenResponse = new TokenResponseDTO();
        tokenResponse.setToken(token);
        setIssueAtAndExpirationToken(tokenResponse);
        tokenResponse.setRoles(getRoles);
        return tokenResponse;
    }

    private static void setIssueAtAndExpirationToken(TokenResponseDTO tokenResponse) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expiresAt = issuedAt.plusMinutes(15);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String  issuedAtString = issuedAt.format(dateTimeFormatter);
        String  expiresAtString = expiresAt.format(dateTimeFormatter);
        tokenResponse.setCreated(issuedAtString);
        tokenResponse.setExpirationToken(expiresAtString);
    }

    private void authenticate(LoginModel login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(), login.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @ApiOperation(value="Logout Employees in the dashboard",
            notes = "will only work when you are  LogIn",
            response = Void.class, code = 200
    )
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
