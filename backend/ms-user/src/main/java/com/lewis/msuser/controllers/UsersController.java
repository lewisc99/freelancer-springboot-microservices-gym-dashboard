package com.lewis.msuser.controllers;

import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("v1/users")
public class UsersController {

    @Autowired
    private UserConvert userConvert;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UsersDTO> get(@ModelAttribute PageModel pageModel)
    {
        UsersDTO usersDTO = userService.findAll(pageModel);
        UsersDTO  usersHATEOAS = userConvert.toHateoas(usersDTO, pageModel);
        return ResponseEntity.ok(usersHATEOAS);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable String id)
    {
        User userDTO =  userService.findById(UUID.fromString(id));
        return ResponseEntity.ok(userDTO);
    }

}
