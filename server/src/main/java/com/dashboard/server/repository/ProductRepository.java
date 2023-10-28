package com.dashboard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dashboard.server.entity.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
