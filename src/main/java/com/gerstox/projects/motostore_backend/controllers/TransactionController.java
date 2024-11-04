package com.gerstox.projects.motostore_backend.controllers;

import com.gerstox.projects.motostore_backend.dtos.TransactionDTO;
import com.gerstox.projects.motostore_backend.entities.Transaction;
import com.gerstox.projects.motostore_backend.services.TransactionService;
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
@RequestMapping("/api/v1/transaction")
public class TransactionController {

  @Autowired private TransactionService transactionService;

  @PostMapping("/streaming")
  public ResponseEntity<?> crearTransaccionConCuentaStreaming(
      @Valid @RequestBody TransactionDTO transactionDTO, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    Transaction transaction =
        transactionService.createTransactionWithStreaming(
            transactionDTO.getClientId(),
            transactionDTO.getServiceId(),
            transactionDTO.getAmount());
    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }

  @PostMapping("/recharge")
  public ResponseEntity<?> crearTransaccionConRecargaSaldo(
      @Valid @RequestBody TransactionDTO transactionDTO, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    Transaction transaction =
        transactionService.createTransactionWithRecharge(
            transactionDTO.getClientId(),
            transactionDTO.getServiceId(),
            transactionDTO.getAmount());
    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }

  @PostMapping("/license")
  public ResponseEntity<?> crearTransaccionConLicenciaPrograma(
      @Valid @RequestBody TransactionDTO transactionDTO, BindingResult result) {

    if (result.hasFieldErrors()) {
      return DataValidation.validateDTO(result);
    }

    Transaction transaccion =
        transactionService.createTransactionWithLicense(
            transactionDTO.getClientId(),
            transactionDTO.getServiceId(),
            transactionDTO.getAmount());
    return new ResponseEntity<>(transaccion, HttpStatus.CREATED);
  }
}
