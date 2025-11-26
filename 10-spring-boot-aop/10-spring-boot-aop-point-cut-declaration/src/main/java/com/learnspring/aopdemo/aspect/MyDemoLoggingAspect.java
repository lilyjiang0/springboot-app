package com.learnspring.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // Add all related advices for logging.

    // Match any return types, specific package name, any classes, any methods, any params.
    @Pointcut("execution(* com.learnspring.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // Apply pointcut declaration.
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ========>>> Executing @Before advice");
    }

    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n ========>>> Performing API analytics");
    }
}
