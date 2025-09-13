package com.learnspring.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    // Create a controller method to show initial HTML form.
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }


    // Create a controller method to read form data and
    // add data to the model.
    @RequestMapping("/processForm")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        // Read the request parameter from the HTML form.
        String name = request.getParameter("studentName");
        // Convert the data to all uppercase.
        name = name.toUpperCase();
        // Create the message.
        String msg = "Yo! " + name;
        // Add message to the model.
        model.addAttribute("message", msg);

        return "helloworld";
    }
}
