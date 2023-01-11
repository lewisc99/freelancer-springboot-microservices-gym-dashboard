package com.lewis.msemployee.repositories;

import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.repositories.contracts.RolesDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RolesDaoImpl  implements RolesDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Roles findRolesByName(String roleName) {
        Session session = entityManager.unwrap(Session.class);
        Query<Roles> query = session.createQuery("select i from Roles i  WHERE name=:roleName", Roles.class);
        query.setParameter("roleName",roleName);
        Roles role = query.getSingleResult();
        return role;
    }
}
