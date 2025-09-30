package com.learnspring.thymeleafdemo.controller;

import com.learnspring.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Value("${countries}")
    private List<String> countries;
    @Value("${languages}")
    private List<String> languages;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // Create a student object.
        Student student = new Student();
        // Add student object to the model.
        model.addAttribute("student", student);
        // Add the list of countries to the model.
        model.addAttribute("countries", countries);
        // Add the list of languages to the model.
        model.addAttribute("languages", languages);
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student student) {
        // Log the input data.
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return "student-confirmation";
    }
}
