package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Instructor;
import com.learnspring.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
