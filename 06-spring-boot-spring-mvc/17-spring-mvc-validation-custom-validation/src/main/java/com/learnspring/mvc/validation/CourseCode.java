package com.learnspring.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    // Define default course code.
    public String value() default "ABC";
    // Define default error message.
    public String message() default "must start with 'ABC'";
    // Define default groups to group related constraints.
    public Class<?>[] groups() default {};
    // Define default payloads which provides custom details about validation failure
    // e.g. severity level, error code.
    public Class<? extends Payload>[] payload() default {};
}
