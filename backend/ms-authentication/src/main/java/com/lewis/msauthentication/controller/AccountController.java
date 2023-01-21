package com.lewis.msauthentication.controller;


import com.lewis.msauthentication.entities.models.LoginModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/account")
public class AccountController {


//    @PostMapping(value="login")
//    public ResponseEntity<Void> logIn(@RequestBody LoginModel login) throws Exception
//    {
//        try
//        {
//
//        }
//        catch (BadCredentialsException e)
//        {
//            throw new Exception("Incorrect username or password",e);
//        }
//        return ResponseEntity.ok().build();
//    }
}
