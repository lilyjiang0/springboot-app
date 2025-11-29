package com.learnspring.aopdemo.aspect;
import org.aspectj.lang.annotation.Pointcut;


public class AopExpressions {
    // Add all related advices for logging.

    // Match any return types, specific package name, any classes, any methods, any params.
    @Pointcut("execution(* com.learnspring.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // Create a  pointcut for getter methods.
    @Pointcut("execution(* com.learnspring.aopdemo.dao.*.get*(..))")
    public void forGetter() {}

    // Pointcut for setter methods.
    @Pointcut("execution(* com.learnspring.aopdemo.dao.*.set*(..))")
    public void forSetter() {}

    // Combine pointcut, include package but exclude getter and setters.
    @Pointcut("forDaoPackage() && !(forGetter() || forSetter())")
    public void forDaoPackageNoGetterSetter() {}

}
