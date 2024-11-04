package com.gerstox.projects.motostore_backend.services.impl;

import com.gerstox.projects.motostore_backend.entities.License;
import com.gerstox.projects.motostore_backend.entities.Recharge;
import com.gerstox.projects.motostore_backend.entities.Streaming;
import com.gerstox.projects.motostore_backend.entities.Transaction;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.exceptions.ClientNotFoundException;
import com.gerstox.projects.motostore_backend.exceptions.ServiceNotFoundException;
import com.gerstox.projects.motostore_backend.repositories.LicenseRepository;
import com.gerstox.projects.motostore_backend.repositories.RechargeRepository;
import com.gerstox.projects.motostore_backend.repositories.StreamingRepository;
import com.gerstox.projects.motostore_backend.repositories.TransactionRepository;
import com.gerstox.projects.motostore_backend.repositories.UserRepository;
import com.gerstox.projects.motostore_backend.services.TransactionService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired private TransactionRepository transactionRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private StreamingRepository streamingRepository;

  @Autowired private LicenseRepository licenseRepository;

  @Autowired private RechargeRepository rechargeRepository;

  @Override
  public Transaction createTransactionWithStreaming(
      Long clientId, Long streamingId, Double amount) {

    User client =
        userRepository
            .findById(clientId)
            .orElseThrow(
                () -> new ClientNotFoundException("Cliente con id " + clientId + " no encontrado"));

    Streaming service =
        streamingRepository
            .findById(streamingId)
            .orElseThrow(
                () ->
                    new ServiceNotFoundException(
                        "Servicio con id " + streamingId + " no encontrado"));

    Transaction transaction =
        Transaction.builder().client(client).service(service).amount(amount).createdAt(new Date()).build();

    return transactionRepository.save(transaction);
  }

  @Override
  public Transaction createTransactionWithLicense(Long clientId, Long licenseId, Double amount) {
    User client =
        userRepository
            .findById(clientId)
            .orElseThrow(
                () -> new ClientNotFoundException("Cliente con id " + clientId + " no encontrado"));

    License service =
        licenseRepository
            .findById(licenseId)
            .orElseThrow(
                () ->
                    new ServiceNotFoundException(
                        "Servicio con id " + licenseId + " no encontrado"));

    Transaction transaction =
        Transaction.builder().client(client).service(service).amount(amount).createdAt(new Date()).build();

    return transactionRepository.save(transaction);
  }

  @Override
  public Transaction createTransactionWithRecharge(Long clientId, Long rechargeId, Double amount) {
    User client =
        userRepository
            .findById(clientId)
            .orElseThrow(
                () -> new ClientNotFoundException("Cliente con id " + clientId + " no encontrado"));

    Recharge service =
        rechargeRepository
            .findById(rechargeId)
            .orElseThrow(
                () ->
                    new ServiceNotFoundException(
                        "Servicio con id " + rechargeId + " no encontrado"));

    Transaction transaction =
        Transaction.builder().client(client).service(service).amount(amount).createdAt(new Date()).build();

    return transactionRepository.save(transaction);
  }
}
