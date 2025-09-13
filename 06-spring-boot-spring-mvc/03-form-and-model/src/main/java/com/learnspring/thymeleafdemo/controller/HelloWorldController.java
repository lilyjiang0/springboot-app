package com.learnspring.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    // Create a controller method to show initial HTML form.
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // Create a controller method to process the HTML form.
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }
}
