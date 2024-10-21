package com.gerstox.projects.motostore_backend.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gerstox.projects.motostore_backend.annotations.impl.IsUniqueValidation;

@Constraint(validatedBy = IsUniqueValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IsUnique {

  String message() default "{IsUnique.register.username}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
