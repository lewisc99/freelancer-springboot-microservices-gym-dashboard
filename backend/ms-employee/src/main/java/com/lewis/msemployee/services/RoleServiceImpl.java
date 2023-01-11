package com.lewis.msemployee.services;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.repositories.contracts.RolesDao;
import com.lewis.msemployee.services.contracts.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RolesDao rolesDao;

    @Override
    public List<Roles> findRolesByName(List<String> rolesName) {

        List<Roles> roles = new ArrayList<>();
        for (String roleName: rolesName)
        {
            Roles role =   rolesDao.findRolesByName(roleName);
            roles.add(role);
        }
        return roles;
    }
}
