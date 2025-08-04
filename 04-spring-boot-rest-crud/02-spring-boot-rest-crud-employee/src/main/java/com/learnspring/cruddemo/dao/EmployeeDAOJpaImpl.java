package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    // Define field for entitymanager.
    private EntityManager entityManager;

    // Set up constructor injection.
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query.
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        // Execute query and get result list.
        List<Employee> employees = query.getResultList();
        // Return the results.
        return employees;
    }

    @Override
    public Employee findById(int id) {
        // Get employee.
        Employee employee = entityManager.find(Employee.class, id);
        // Return employee.
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        // Save employee.
        // Merge means insert or update. If id == 0, insert. Else update.
        Employee dbEmployee = entityManager.merge(employee);
        // Return the dbEmployee.
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        // Find employee by id.
        Employee employee = entityManager.find(Employee.class, id);
        // Remove employee.
        entityManager.remove(employee);
    }


}
