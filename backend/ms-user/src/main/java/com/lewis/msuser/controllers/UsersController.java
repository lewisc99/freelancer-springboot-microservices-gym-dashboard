package com.lewis.msuser.controllers;

import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.Message;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.entities.models.MailEvent;
import com.lewis.msuser.entities.models.MessageModel;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.entities.models.UserModel;
import com.lewis.msuser.services.contracts.UserService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Autowired
    private KafkaTemplate<String, Serializable> jsonKafkaTemplate;


    @ApiOperation(value="create a new user",
            notes = "will create a new User, you must add valid properties",
            response = Void.class, code = 201
           )
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserModel userModel, HttpServletRequest request)
    {
        User user = mapper.map(userModel,User.class);
        userService.create(user);

        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value="get all users",
            notes = "you must add parameters pagNumber and pagSize and sortBy",
            response = UsersDTO.class, code = 200
    )
    @GetMapping
    public ResponseEntity<UsersDTO> get(@ModelAttribute PageModel pageModel)
    {
        Page<User> page = userService.findAll(pageModel);
        List<UserDTO> usersConvertedToDTO = userConvert.toUsersDTO(page.toList());
        UsersDTO usersDTO = userConvert.toUsersWithPagination(pageModel, page, usersConvertedToDTO);

        UsersDTO  usersHATEOAS = userConvert.toHateoas(usersDTO, pageModel);
        return ResponseEntity.ok(usersHATEOAS);
    }


    @ApiOperation(value="get user By Id",
            notes = "you must add a valid Id of type UUID",
            response = UserDTO.class, code = 200
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable(required = true) String id)
    {
        User user =  userService.findById(UUID.fromString(id));
        UserDTO userDTO = userConvert.toUserHATEOAS(user);
        return ResponseEntity.ok(userDTO);
    }

    @ApiOperation(value="to Update an User",
            notes = "you must add valid Id and valid properties to update the Ids it's not require only the categoryId.",
            response = Void.class, code = 204
    )
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable(required = true) String id, @Valid @RequestBody UserModel userModel)
    {
        userModel.setId(UUID.fromString(id));
        userService.update(userModel);
        return ResponseEntity.status(204).build();
    }

    @ApiOperation(value="to Delete an User",
            notes = "you must add a valid Id type of UUID",
            response = Void.class, code = 204
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid  @PathVariable(required = true) String id)
    {
        userService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/message")
    public ResponseEntity<Void> saveMessage(@RequestBody  @Valid MessageModel messageModel)
    {
        User user = userService.findById(messageModel.getUser());
        Message message = mapper.map(messageModel, Message.class);
        message.setUser(user);
        userService.saveMessage(message);
        LocalDate today = LocalDate.now();
        jsonKafkaTemplate.send("user-message-topic", new MailEvent(user.getUsername(),user.getEmail(), messageModel.getSubject(), messageModel.getText(), today));
        return ResponseEntity.noContent().build();
    }

}
