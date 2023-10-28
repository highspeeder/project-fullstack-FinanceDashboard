package com.dashboard.server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dashboard.server.entity.product.Product;
import com.dashboard.server.entity.product.ProductTransaction;
import com.dashboard.server.entity.product.Transaction;

public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Long> {
    List<ProductTransaction> findByProduct(Product product);

    List<ProductTransaction> findByTransactionId(String transactionId);
}
