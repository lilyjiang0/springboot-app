package com.learnspring.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // Add all related advices for logging.

    // Match method starting with "add" and return type is void.
    // @Before("execution(void add*())")

    // Match method starting with "add" and return type is any.
    @Before("execution(* add*())")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ========>>> Executing @Before advice on addAccount()");
    }
}
