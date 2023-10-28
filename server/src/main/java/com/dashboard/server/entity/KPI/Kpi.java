package com.dashboard.server.entity.KPI;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@EntityListeners(AuditingEntityListener.class) // CreatedDate, LastModifiedDate어노테이션 사용 가능
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kpi")
public class Kpi {
        @Id
        private String id;

        @Column
        private BigDecimal totalProfit;

        @Column
        private BigDecimal totalRevenue;

        @Column
        private BigDecimal totalExpenses;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "kpi_id")
        private List<MonthData> monthData;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "kpi_id")
        private List<DayData> dayData;

        @Embedded // expensesByCategory를 DB에서 kpi테이블의 컬럼으로 저장해준다.
        private ExpensesByCategory expensesByCategory;

        @Embeddable
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ExpensesByCategory {
                private BigDecimal salaries;
                private BigDecimal supplies;
                private BigDecimal services;
        }
}
