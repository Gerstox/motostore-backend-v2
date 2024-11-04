package com.gerstox.projects.motostore_backend.controllers;

import com.gerstox.projects.motostore_backend.dtos.UserDTO;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.services.UserService;
import com.gerstox.projects.motostore_backend.utils.DataValidation;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping
  public ResponseEntity<Page<User>> findAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int elements) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(page, elements));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {

    Optional<User> userOptional = userService.findById(id);

    if (userOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @PreAuthorize(value = "'Denegado'")
  public ResponseEntity<?> save(@Valid @RequestBody UserDTO user, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    Optional<User> userOptional = userService.delete(id);

    if (userOptional.isPresent()) {
      return ResponseEntity.ok(userOptional.orElseThrow());
    }

    return ResponseEntity.badRequest().build();
  }
}
