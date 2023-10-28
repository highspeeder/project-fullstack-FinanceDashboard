package com.dashboard.server.service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dashboard.server.dto.product.ProductDto;
import com.dashboard.server.dto.product.TransactionDto;
import com.dashboard.server.entity.product.ProductTransaction;
import com.dashboard.server.repository.ProductTransactionRepository;
import com.dashboard.server.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    private final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    @Transactional
    public List<TransactionDto> getAllTransactions() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[TransactionService] getAllTransactions 시작");

        List<TransactionDto> transactions = transactionRepository.findTop50ByOrderByCreatedAtAsc().stream()
                .map(transaction -> {
                    List<ProductTransaction> productTransactions = productTransactionRepository
                            .findByTransactionId(transaction.getId());

                    List<ProductDto> productDtos = productTransactions.stream()
                            .map(pt -> {
                                var p = pt.getProduct();
                                return ProductDto.builder()
                                        .id(p.getId())
                                        .price(p.getPrice())
                                        .expense(p.getExpense())
                                        .createdAt(p.getCreatedAt())
                                        .updatedAt(p.getUpdatedAt())
                                        .build();
                            })
                            .collect(Collectors.toList());

                    var transactionDto = TransactionDto.builder()
                            .id(transaction.getId())
                            .amount(transaction.getAmount())
                            .buyer(transaction.getBuyer())
                            .products(productDtos)
                            .createdAt(transaction.getCreatedAt())
                            .updatedAt(transaction.getUpdatedAt())
                            .build();

                    return transactionDto;
                })
                .collect(Collectors.toList());

        long stopTime = System.currentTimeMillis();
        LOGGER.info("[TransactionService] getAllTransactions 종료 - 총시간: " + (stopTime - startTime));

        return transactions;
    }
}