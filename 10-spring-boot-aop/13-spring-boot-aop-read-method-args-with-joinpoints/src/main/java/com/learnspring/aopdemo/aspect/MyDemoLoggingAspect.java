package com.learnspring.aopdemo.aspect;

import com.learnspring.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
    // Add all related advices for logging.
    // Apply pointcut declaration.
    @Before("com.learnspring.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n ========>>> Executing @Before advice");
        // Display method signature.
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);
        // Display method arguments.
        // Get args.
        Object[] args = joinPoint.getArgs();
        // Loop through args and print.
        for(Object arg : args) {
            System.out.println(arg);
            if (arg instanceof Account) {
                // Downcast and print account specific stuff.
                Account account = (Account) arg;
                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }
    }

}
