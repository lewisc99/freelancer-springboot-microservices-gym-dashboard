package com.lewis.msuser.controllers;

import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import com.lewis.msuser.entities.models.pageModel;
import com.lewis.msuser.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UsersController {

    @Autowired
    private UserConvert userConvert;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> get(@ModelAttribute pageModel pageModel)
    {
            List<User> usersEntity = userService.findAll(pageModel.getPagNumber(), pageModel.getPagSize(), pageModel.getSortBy());
            List<UserDTO> usersDTO = userConvert.toListUserDTO(usersEntity);
            

        return ResponseEntity.ok(usersDTO);
    }


}
