package com.lewis.msemployee.repositories.contracts;

import com.lewis.msemployee.entities.domain.Roles;

import java.util.List;

public interface RolesDao {
    Roles findRolesByName(String roleName);
    List<Roles> findAll();
}
