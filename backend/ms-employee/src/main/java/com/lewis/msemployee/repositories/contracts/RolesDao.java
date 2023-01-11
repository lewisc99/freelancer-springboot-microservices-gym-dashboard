package com.lewis.msemployee.repositories.contracts;

import com.lewis.msemployee.entities.domain.Roles;

public interface RolesDao {
    Roles findRolesByName(String roleName);
}
