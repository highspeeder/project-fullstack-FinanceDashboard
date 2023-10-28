package com.dashboard.server;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.dashboard.server.entity.KPI.Kpi;
import com.dashboard.server.entity.product.Product;
import com.dashboard.server.entity.product.ProductTransaction;
import com.dashboard.server.entity.product.Transaction;
import com.dashboard.server.repository.KpiRepository;
import com.dashboard.server.repository.ProductRepository;
import com.dashboard.server.repository.ProductTransactionRepository;
import com.dashboard.server.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class fileLoader implements ApplicationRunner {
    @Autowired
    private KpiRepository kpiRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // kpi데이터 db에 저장
        var kpiCount = kpiRepository.count();
        if (kpiCount == 0) {
            Resource resource = resourceLoader.getResource("classpath:data/kpi.json");
            String file = resource.getContentAsString(Charset.forName("UTF-8"));

            file = file.replace("$", "");

            // 파일을 읽고 JSON 데이터로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            Kpi kpi = objectMapper.readValue(file, Kpi.class);

            // 변환된 데이터 활용
            kpiRepository.save(kpi);
        }

        // product데이터 db에 저장
        var productCount = productRepository.count();
        if (productCount == 0) {
            // product저장
            Resource resource = resourceLoader.getResource("classpath:data/products.json");
            String file = resource.getContentAsString(Charset.forName("UTF-8"));

            file = file.replace("$", "");

            // 파일을 읽고 JSON 데이터로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            Product[] products = objectMapper.readValue(file, Product[].class);

            for (Product product : products) {
                productRepository.save(product);
            }

            // transaction저장
            Resource resource2 = resourceLoader.getResource("classpath:data/transactions.json");
            String file2 = resource2.getContentAsString(Charset.forName("UTF-8"));

            file2 = file2.replace("$", "");

            // 파일을 읽고 JSON 데이터로 변환
            ObjectMapper objectMapper2 = new ObjectMapper();
            Transaction[] transactions = objectMapper2.readValue(file2, Transaction[].class);

            for (Transaction transaction : transactions) {
                transactionRepository.save(transaction);
            }

            // // Product와 Transaction 간의 다대다 관계를 표현하는 ProductTransaction 데이터 생성 및 저장
            // for (Product product : products) {
            // for (String transactionId : product.getTransactions()) {
            // for (Transaction transaction : transactions) {
            // if (transactionId.equals(transaction.getId())) {
            // ProductTransaction productTransaction = new ProductTransaction();
            // productTransaction.setProduct(product);
            // productTransaction.setTransaction(transaction);

            // productTransactionRepository.save(productTransaction);
            // }
            // }
            // }
            // }
            // for (Transaction transaction : transactions) {
            // for (String productId : transaction.getProducts()) {
            // for (Product product : products) {
            // if (productId.equals(product.getId())) {
            // ProductTransaction productTransaction = new ProductTransaction();
            // productTransaction.setProduct(product);
            // productTransaction.setTransaction(transaction);

            // productTransactionRepository.save(productTransaction);
            // }
            // }
            // }
            // }
        }
    }
}
