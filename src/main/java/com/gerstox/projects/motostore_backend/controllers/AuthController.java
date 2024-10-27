package com.gerstox.projects.motostore_backend.controllers;

import com.gerstox.projects.motostore_backend.dtos.UserLoginDTO;
import com.gerstox.projects.motostore_backend.dtos.UserRegisterDTO;
import com.gerstox.projects.motostore_backend.services.AuthService;
import com.gerstox.projects.motostore_backend.utils.DataValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private AuthService service;

  @PostMapping("/register")
  public ResponseEntity<?> register(
      @Valid @RequestBody UserRegisterDTO user, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    service.register(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO user, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    // service.login(user);
    return ResponseEntity.ok("JWT token");
  }
}
