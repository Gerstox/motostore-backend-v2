package com.gerstox.projects.motostore_backend.controllers;

import com.gerstox.projects.motostore_backend.entities.Streaming;
import com.gerstox.projects.motostore_backend.services.StreamingService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service/streaming")
public class StreamingController {

  @Autowired private StreamingService streamingService;

  @GetMapping
  public ResponseEntity<Page<Streaming>> findAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int elements) {
    return ResponseEntity.status(HttpStatus.OK).body(streamingService.findAll(page, elements));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {

    Optional<Streaming> streamingOptional = streamingService.findById(id);

    if (streamingOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(streamingService.findById(id));
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @PreAuthorize(value = "'Denegado'")
  public ResponseEntity<?> save(@Valid @RequestBody Streaming streaming, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(streamingService.save(streaming));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProvider(
      @PathVariable("id") Long id, @Valid @RequestBody Streaming streaming, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    Optional<Streaming> streamingOptional = streamingService.update(id, streaming);

    if (streamingOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(streamingOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    Optional<Streaming> streamingOptional = streamingService.delete(id);

    if (streamingOptional.isPresent()) {
      return ResponseEntity.ok(streamingOptional.orElseThrow());
    }

    return ResponseEntity.badRequest().build();
  }
}
