package com.learnspring.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    // Create a controller method to show initial HTML form.
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }


    // Create a controller method to read form data and
    // add data to the model.
    @PostMapping("/processForm")
    public String processForm(@RequestParam("studentName") String name, Model model) {
        // Convert the data to all uppercase.
        name = name.toUpperCase();
        // Create the message.
        String msg = "Hey my friend! " + name;
        // Add message to the model.
        model.addAttribute("message", msg);

        return "helloworld";
    }
}
