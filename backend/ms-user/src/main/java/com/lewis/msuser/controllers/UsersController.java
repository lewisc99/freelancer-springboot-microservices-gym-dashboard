package com.lewis.msuser.controllers;

import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.entities.models.UserModel;
import com.lewis.msuser.services.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("v1/users")
public class UsersController {

    @Autowired
    private UserConvert userConvert;
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserModel userModel)
    {
        User user = mapper.map(userModel,User.class);
        userService.create(user);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/usermodel")
    public ResponseEntity<UserModel> get()
    {
        return ResponseEntity.ok(new UserModel());
    }

    @GetMapping
    public ResponseEntity<UsersDTO> get(@ModelAttribute PageModel pageModel)
    {
        Page<User> page = userService.findAll(pageModel);
        List<UserDTO> usersConvertedToDTO = userConvert.toUsersDTO(page.toList());
        UsersDTO usersDTO = userConvert.toUsersWithPagination(pageModel, page, usersConvertedToDTO);

        UsersDTO  usersHATEOAS = userConvert.toHateoas(usersDTO, pageModel);
        return ResponseEntity.ok(usersHATEOAS);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id)
    {
        User user =  userService.findById(UUID.fromString(id));
        UserDTO userDTO = userConvert.toUserHATEOAS(user);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        userService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
