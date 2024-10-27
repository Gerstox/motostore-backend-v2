package com.gerstox.projects.motostore_backend.controllers.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service/streaming")
public class StreamingController {

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body("Obteniendo todos los servicios de streaming");
  }
}
