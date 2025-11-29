package com.luv2code.springboot.thymeleafdemo.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    // Setup logger.
    private Logger logger = Logger.getLogger(getClass().getName());

    // Setup pointcut declarations.
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {}

    // Add @Before advice.
    @Before("forAppFlow()")
    public void before(JoinPoint joinpoint) {
        // Display method we are calling.
        MethodSignature methodName = (MethodSignature) joinpoint.getSignature();
        logger.info("========> @Before: calling method: " + methodName);
        // Display arguments.
        Object[] args = joinpoint.getArgs();
        for (Object arg : args) {
            logger.info("======>> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // Display method we are calling.
        MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
        logger.info("========> @AfterReturning: calling method: " + methodName);
        // Display data returned
        logger.info("========> Result: " + result);
    }
}
