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

    // Create a  pointcut for getter methods.
    @Pointcut("execution(* com.learnspring.aopdemo.dao.*.get*(..))")
    private void forGetter() {}

    // Pointcut for setter methods.
    @Pointcut("execution(* com.learnspring.aopdemo.dao.*.set*(..))")
    private void forSetter() {}

    // Combine pointcut, include package but exclude getter and setters.
    @Pointcut("forDaoPackage() && !(forGetter() || forSetter())")
    private void forDaoPackageNoGetterSetter() {}

    // Apply pointcut declaration.
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ========>>> Executing @Before advice");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n ========>>> Performing API analytics");
    }
}
