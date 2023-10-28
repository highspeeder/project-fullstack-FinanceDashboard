package com.dashboard.server.dto.KPI;

import java.math.BigDecimal;
import java.util.List;
import com.dashboard.server.entity.KPI.Kpi.ExpensesByCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KpiDto {
    private String id;
    private BigDecimal totalProfit; // 총 매출
    private BigDecimal totalRevenue;// 총 이익
    private BigDecimal totalExpenses; // 총 비용

    private List<DayDataDto> dayData;
    private List<MonthDataDto> monthData;

    private ExpensesByCategory expensesByCategory;
}
