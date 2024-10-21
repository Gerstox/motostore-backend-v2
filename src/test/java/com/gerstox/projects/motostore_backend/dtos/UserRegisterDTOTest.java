package com.gerstox.projects.motostore_backend.dtos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserRegisterDTOTest {

  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidUser() {
    UserRegisterDTO usuario = new UserRegisterDTO();
    usuario.setUsername("username");
    usuario.setPassword("password123");
    usuario.setName("name");
    usuario.setIdentification("identification");

    Set<ConstraintViolation<UserRegisterDTO>> violations = validator.validate(usuario);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void testEmptyUsername() {
    UserRegisterDTO usuario = new UserRegisterDTO();
    usuario.setUsername("");
    usuario.setPassword("password123");
    usuario.setName("name");
    usuario.setIdentification("identification");

    Set<ConstraintViolation<UserRegisterDTO>> violations = validator.validate(usuario);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void testEmptyPassword() {
    UserRegisterDTO usuario = new UserRegisterDTO();
    usuario.setUsername("username");
    usuario.setPassword("");
    usuario.setName("name");
    usuario.setIdentification("identification");

    Set<ConstraintViolation<UserRegisterDTO>> violations = validator.validate(usuario);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void testEmptyName() {
    UserRegisterDTO usuario = new UserRegisterDTO();
    usuario.setUsername("username");
    usuario.setPassword("password123");
    usuario.setName("");
    usuario.setIdentification("identification");

    Set<ConstraintViolation<UserRegisterDTO>> violations = validator.validate(usuario);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void testEmptyIdentification() {
    UserRegisterDTO usuario = new UserRegisterDTO();
    usuario.setUsername("username");
    usuario.setPassword("password123");
    usuario.setName("name");
    usuario.setIdentification("");

    Set<ConstraintViolation<UserRegisterDTO>> violations = validator.validate(usuario);
    assertFalse(violations.isEmpty());
  }
}
