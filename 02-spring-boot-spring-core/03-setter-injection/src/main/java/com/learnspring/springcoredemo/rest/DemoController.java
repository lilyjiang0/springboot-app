package com.learnspring.springcoredemo.rest;

import com.learnspring.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // Define a private field for the dependency
    private Coach myCoach;

    // setter injection, setxxx or can be any function name
//    @Autowired
//    public void setMyCoach(Coach coach) {
//        myCoach = coach;
//    }

    @Autowired
    public void findMyCoach(Coach coach) {
        myCoach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
