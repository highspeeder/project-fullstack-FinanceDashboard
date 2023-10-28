package com.dashboard.server.dto.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDto {

    private String id;

    private BigDecimal amount;

    private String buyer;

    @JsonSerialize(contentUsing = ProductDtoSerializer.class)
    private List<ProductDto> products;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
}
