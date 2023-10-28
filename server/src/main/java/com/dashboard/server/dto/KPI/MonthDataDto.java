package com.dashboard.server.dto.KPI;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthDataDto {
    private Long id;

    private String month;

    private BigDecimal revenue;

    private BigDecimal expenses;

    private BigDecimal operationalExpenses;

    private BigDecimal nonOperationalExpenses;
}
