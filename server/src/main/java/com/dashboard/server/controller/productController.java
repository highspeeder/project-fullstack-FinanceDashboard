package com.dashboard.server.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dashboard.server.dto.product.ProductDto;
import com.dashboard.server.service.KpiService;
import com.dashboard.server.service.ProductService;

@RestController
@RequestMapping("/product")
public class productController {
    @Autowired
    ProductService productService;

    @Autowired
    KpiService kpiService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
