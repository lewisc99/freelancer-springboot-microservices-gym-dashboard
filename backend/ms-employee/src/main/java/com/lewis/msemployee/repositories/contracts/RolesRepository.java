package com.lewis.msemployee.repositories.contracts;
import com.lewis.msemployee.entities.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {

    Roles findRolesByName(String roles);
}
