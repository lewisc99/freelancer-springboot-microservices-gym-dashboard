package com.lewis.msemployee.services;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.repositories.contracts.RolesDao;
import com.lewis.msemployee.services.contracts.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RolesServiceImplTest {


    @MockBean
    private RolesDao rolesDao;

    @Autowired
    private RoleService roleService;

    private Roles role = new Roles();

    @BeforeEach
    public void beforeEach()
    {
        role.setId(UUID.fromString("ab7f3c91-6a92-429e-b090-b2e8754feb4a"));
        role.setName("admin");
    }

    @Test
    @DisplayName("findRoleByName return role")
    public void findRoleByNameReturnRole()
    {   List<String> roles = new ArrayList<String>();
        roles.add("admin");

        when(rolesDao.findRolesByName("admin")).thenReturn(role);
        List<Roles> role = roleService.findRolesByName(roles);

        assertEquals("admin",role.get(0).getName());
    }

    @Test
    @DisplayName("findRoleByName then Throw Exception")
    public void findRoleByNameThrowException()
    {
        List<String> roles = new ArrayList<String>();
        roles.add("admin");

        when(rolesDao.findRolesByName("admin")).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {roleService.findRolesByName(roles);});
    }

}
