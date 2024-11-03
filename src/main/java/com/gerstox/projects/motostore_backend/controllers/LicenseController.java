package com.gerstox.projects.motostore_backend.controllers;

import com.gerstox.projects.motostore_backend.entities.License;
import com.gerstox.projects.motostore_backend.services.LicenseService;
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
@RequestMapping("/api/v1/service/license")
public class LicenseController {

  @Autowired private LicenseService licenseService;

  @GetMapping
  public ResponseEntity<Page<License>> findAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int elements) {
    return ResponseEntity.status(HttpStatus.OK).body(licenseService.findAll(page, elements));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") int id) {

    Optional<License> licenseOptional = licenseService.findById(id);

    if (licenseOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(licenseService.findById(id));
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @PreAuthorize(value = "'Denegado'")
  public ResponseEntity<?> save(@Valid @RequestBody License license, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.save(license));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProvider(
      @PathVariable("id") int id, @Valid @RequestBody License license, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    Optional<License> licenseOptional = licenseService.update(id, license);

    if (licenseOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(licenseOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") int id) {

    Optional<License> licenseOptional = licenseService.delete(id);

    if (licenseOptional.isPresent()) {
      return ResponseEntity.ok(licenseOptional.orElseThrow());
    }

    return ResponseEntity.badRequest().build();
  }
}
