package com.dashboard.server.entity.KPI;

import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DayData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private BigDecimal revenue;

    private BigDecimal expenses;

    @ManyToOne
    @JoinColumn(name = "kpi_id")
    private Kpi kpi;

}
