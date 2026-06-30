package com.app.hitesh.repository;

import com.app.hitesh.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository
        extends JpaRepository<Transaction,Integer> {
}