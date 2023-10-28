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
import com.dashboard.server.repository.ProductRepository;
import com.dashboard.server.repository.ProductTransactionRepository;

@Service
public class ProductService {
    private final Logger LOGGER = LoggerFactory.getLogger(KpiService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    public List<ProductDto> getAllProducts() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductService] getAllProducts 시작");

        List<ProductDto> products = productRepository.findAll().stream()
                .map(product -> {
                    List<ProductTransaction> productTransactions = productTransactionRepository
                            .findByProduct(product);

                    List<TransactionDto> transactionDtos = productTransactions.stream()
                            .map(pt -> {
                                var t = pt.getTransaction();
                                return TransactionDto.builder()
                                        .id(t.getId())
                                        .amount(t.getAmount())
                                        .buyer(t.getBuyer())
                                        .createdAt(t.getCreatedAt())
                                        .updatedAt(t.getUpdatedAt())
                                        .build();
                            })
                            .collect(Collectors.toList());

                    var productDto = ProductDto.builder()
                            .id(product.getId())
                            .price(product.getPrice())
                            .expense(product.getExpense())
                            .transactions(transactionDtos)
                            .createdAt(product.getCreatedAt())
                            .updatedAt(product.getUpdatedAt())
                            .build();

                    return productDto;
                })
                .collect(Collectors.toList());

        long stopTime = System.currentTimeMillis();
        LOGGER.info("[ProductService] getAllProducts 종료 - 총시간: " + (stopTime - startTime));

        return products;
    }
}
