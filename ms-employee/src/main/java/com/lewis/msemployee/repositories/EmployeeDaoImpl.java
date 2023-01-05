package com.lewis.msemployee.repositories;

import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EntityManager entityManager;


    @Override
    public void create(Employee employee)
    {
        try
        {
            Session session = entityManager.unwrap(Session.class);
            session.save(employee);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    @Override
    public Employee getById(UUID id) {

        try {

            Session session = entityManager.unwrap(Session.class);

            Employee employee = session.get(Employee.class, id);

            return employee;

        } catch (Exception e)
        {
            e.getStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }
}
