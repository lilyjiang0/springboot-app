package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO{
    // Define field for entity manager.
    private EntityManager entityManager;
    // Inject entity manager using constructor injection.
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // Retrieve the instructor.
        Instructor tempInstructor = entityManager.find(Instructor.class, id);
        // Delete the instructor.
        entityManager.remove(tempInstructor);
    }
}
