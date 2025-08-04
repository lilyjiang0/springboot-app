package com.learnspring.springcoredemo.config;

import com.learnspring.springcoredemo.common.Coach;
import com.learnspring.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("swimCoachhhhh")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
