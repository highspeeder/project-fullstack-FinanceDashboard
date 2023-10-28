package com.dashboard.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dashboard.server.entity.product.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findTop50ByOrderByCreatedAtAsc();
}
