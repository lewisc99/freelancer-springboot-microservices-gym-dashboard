package com.lewis.msemployee.repositories;

import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(Employee employee)
    {
            Session session = entityManager.unwrap(Session.class);
            session.save(employee);
    }

    @Override
    public Employee getById(UUID id) {
            Session session = entityManager.unwrap(Session.class);
            Employee employee = session.get(Employee.class, id);

            return employee;
    }


    @Override
    public Employee getByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("select i from Employee i  WHERE email=:email", Employee.class);
        query.setParameter("email",email);
        Employee employee = query.getSingleResult();
        return employee;
    }

    @Override
    public List<Employee> getAll(String sortBy) {
         Session session = entityManager.unwrap(Session.class);
         List<Employee> employees = new ArrayList<>();

         if(sortBy == null)
             employees = session.createQuery("from Employee order by id").getResultList();
         else
             employees = session.createQuery("from Employee order by " + sortBy).getResultList();
         
         return employees;
    }

    @Override
    public Boolean update(Employee employee)
    {
        Session session = entityManager.unwrap(Session.class);
        session.update(employee);
        return true;
    }

    @Override
    public Boolean delete(Employee employee)
    {
        boolean deleted = false;
        try
        {
            Session session = entityManager.unwrap(Session.class);
            employee.getRoles().clear();
            session.delete(employee);
           return deleted = true;
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException();
        }
    }

}
