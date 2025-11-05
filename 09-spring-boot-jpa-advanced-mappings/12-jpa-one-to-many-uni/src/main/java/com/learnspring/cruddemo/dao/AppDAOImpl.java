package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Course;
import com.learnspring.cruddemo.entity.Instructor;
import com.learnspring.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        // Get the courses.
        List<Course> courses = tempInstructor.getCourses();
        // Remove association of all courses.
        for (Course course : courses) {
            course.setInstructor(null);
        }
        // Delete the instructor.
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // Retrieve instructor detail by id.
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, id);
        // Remove the associated object references.
        // Break the bidirectional link.
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        // Delete instructor detail.
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // Create query.
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        query.setParameter("data", id);

        // Execute the query.
        List<Course> courses = query.getResultList();

        return courses;
    }

    /*
    Even with fetch type lazy, the code will still retrieve instructor and courses.
    Join Fetch is similar to eager loading.
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // Create query.
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                    + "JOIN FETCH i.courses "
                    + "JOIN FETCH i.instructorDetail "
                    + "where i.id = :data"
                , Instructor.class
        );
        query.setParameter("data", id);
        // Execute query.
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        // Merge will update a existing entity.
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        // Find course.
        Course course = entityManager.find(Course.class, id);
        // Remove course.
        entityManager.remove(course);
    }

    /*
    Save course and associated reviews.
     */
    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }
}
