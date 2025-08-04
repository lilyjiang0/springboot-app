package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // Define field for entity manager.
    private EntityManager entityManager;

    // Inject entity manager using constructor injection
    // Autowird annotation is optional here, because there is only one constructor.
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Create query.
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);

        // Return query result.
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // Create query.
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
        // Set query parameters.
        query.setParameter("theData", lastName);
        // Return query result.
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);

    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Retrieve the student.
        Student student = entityManager.find(Student.class, id);
        // Delete the student.
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
