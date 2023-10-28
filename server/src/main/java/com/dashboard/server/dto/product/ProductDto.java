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
public class ProductDto {

    private String id;

    private BigDecimal price;

    private BigDecimal expense;

    @JsonSerialize(contentUsing = TransactionDtoSerializer.class)
    private List<TransactionDto> transactions;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
}
