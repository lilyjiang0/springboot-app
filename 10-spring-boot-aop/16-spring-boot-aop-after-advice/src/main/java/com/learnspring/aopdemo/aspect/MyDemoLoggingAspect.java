package com.learnspring.aopdemo.aspect;

import com.learnspring.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
    // Advice for @After on the findAccounts method.
    // Can run in an event of exception or success.
    @After("execution(* com.learnspring.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice (JoinPoint joinPoint){
        // Print out method name.
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("\n =========>> Executing @After on method: " + methodName);
    }

    // Advice for @AfterThrowing on the findAccounts method.
    @AfterThrowing(
            pointcut = "execution(* com.learnspring.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountsAdvice (JoinPoint joinPoint, Throwable exc){
        // Print out method name.
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("\n =========>> Executing @AfterThrowing on method: " + methodName);
        // Log exception.
        System.out.println("\n =========>> The exception is: " + exc);
    }

    // Advice for @AfterReturning on the findAccounts method.
    @AfterReturning(
            pointcut = "execution(* com.learnspring.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        // Print out method name.
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("\n =========>> Executing @AfterReturning on method: " + methodName);
        // Print out results of the method call.
        System.out.println("\n =========>> result is: " + result);

        // Post process the data.
        // Convert the account names to uppercase.
        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account acc : result) {
            // Get and update name to uppercase.
            String upperName = acc.getName().toUpperCase();
            acc.setName(upperName);
        }
    }

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
