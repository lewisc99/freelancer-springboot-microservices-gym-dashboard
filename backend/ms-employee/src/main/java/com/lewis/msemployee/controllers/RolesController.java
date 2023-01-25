package com.lewis.msemployee.controllers;


import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.RolesDto;
import com.lewis.msemployee.services.contracts.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/roles")
public class RolesController {

    @Autowired
    private RoleService roleService;


    @ApiOperation(value="get all Roles",
            notes = "will get all Roles this method is called to help in the frontend to create a form containing all roles",
            response = RolesDto.class
    )
    @GetMapping
    public ResponseEntity<List<RolesDto>> getAll()
    {
        List<Roles> roles = roleService.findAll();
        List<RolesDto> rolesDto =  DtoConverter.ToRolesDto(roles);
        return ResponseEntity.ok(rolesDto);
    }
}
