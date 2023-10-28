package com.dashboard.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.server.dto.product.TransactionDto;
import com.dashboard.server.service.KpiService;
import com.dashboard.server.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    KpiService kpiService;

    @GetMapping(value = "/transactions")
    public ResponseEntity<List<TransactionDto>> getTranactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
