package com.lewis.msemployee.controllers;


import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.RolesDto;
import com.lewis.msemployee.services.contracts.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/roles")
public class RolesController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RolesDto>> getAll()
    {
        List<Roles> roles = roleService.findAll();
        List<RolesDto> rolesDto =  DtoConverter.convertRolesToRolesDto(roles);
        return ResponseEntity.ok(rolesDto);
    }
}
