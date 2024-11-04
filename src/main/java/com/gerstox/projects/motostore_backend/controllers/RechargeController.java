package com.gerstox.projects.motostore_backend.controllers;

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

import com.gerstox.projects.motostore_backend.entities.Recharge;
import com.gerstox.projects.motostore_backend.services.RechargeService;
import com.gerstox.projects.motostore_backend.utils.DataValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/service/recharge")
public class RechargeController {

    @Autowired private RechargeService rechargeService;

  @GetMapping
  public ResponseEntity<Page<Recharge>> findAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int elements) {
    return ResponseEntity.status(HttpStatus.OK).body(rechargeService.findAll(page, elements));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {

    Optional<Recharge> rechargeOptional = rechargeService.findById(id);

    if (rechargeOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(rechargeService.findById(id));
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @PreAuthorize(value = "'Denegado'")
  public ResponseEntity<?> save(@Valid @RequestBody Recharge recharge, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(rechargeService.save(recharge));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProvider(
      @PathVariable("id") Long id, @Valid @RequestBody Recharge recharge, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    Optional<Recharge> rechargeOptional = rechargeService.update(id, recharge);

    if (rechargeOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(rechargeOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    Optional<Recharge> rechargeOptional = rechargeService.delete(id);

    if (rechargeOptional.isPresent()) {
      return ResponseEntity.ok(rechargeOptional.orElseThrow());
    }

    return ResponseEntity.badRequest().build();
  }

}
