package com.dashboard.server.entity.KPI;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class MonthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;

    private BigDecimal revenue;

    private BigDecimal expenses;

    @Column(name = "operational_expenses")
    private BigDecimal operationalExpenses;

    @Column(name = "non_operational_expenses")
    private BigDecimal nonOperationalExpenses;

    @ManyToOne
    @JoinColumn(name = "kpi_id")
    private Kpi kpi;

}
