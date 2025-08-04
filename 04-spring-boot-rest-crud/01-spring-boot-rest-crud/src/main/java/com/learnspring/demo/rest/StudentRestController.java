package com.learnspring.demo.rest;

import com.learnspring.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> studentList;

    // Define @PostConstruct to load the student data only once.
    @PostConstruct
    public void loadData() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Poornima", "Patel"));
        studentList.add(new Student("Mario", "Rossi"));
        studentList.add(new Student("Mary", "Smith"));
    }

    // Define endpoint for "/students"
    // Return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentList;
    }

    // Define endpoint to return student at index, path variable
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // Check the studentId against list size
        if (studentId >= studentList.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return studentList.get(studentId);
    }
}
