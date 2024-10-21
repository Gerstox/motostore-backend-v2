package com.gerstox.projects.motostore_backend.annotations.impl;

import com.gerstox.projects.motostore_backend.annotations.IsUnique;
import com.gerstox.projects.motostore_backend.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsUniqueValidation implements ConstraintValidator<IsUnique, String> {

  @Autowired private UserService service;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return !service.existsByUsername(value);
  }
}
