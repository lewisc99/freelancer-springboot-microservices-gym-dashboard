package com.lewis.msemployee.services.contracts;

import com.lewis.msemployee.entities.domain.Roles;

import java.util.List;

public interface RoleService{
    List<Roles> findRolesByName(List<String> rolesName);
}
