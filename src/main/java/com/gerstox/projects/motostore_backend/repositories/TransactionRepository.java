package com.gerstox.projects.motostore_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerstox.projects.motostore_backend.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
