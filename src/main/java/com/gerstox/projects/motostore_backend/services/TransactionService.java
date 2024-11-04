package com.gerstox.projects.motostore_backend.services;

import com.gerstox.projects.motostore_backend.entities.Transaction;

public interface TransactionService {

    public Transaction createTransactionWithStreaming(Long clientId, Long streamingId, Double amount);

    public Transaction createTransactionWithLicense(Long clientId, Long licenseId, Double amount);

    public Transaction createTransactionWithRecharge(Long clientId, Long rechargeId, Double amount);

}
