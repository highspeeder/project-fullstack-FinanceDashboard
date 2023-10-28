package com.dashboard.server.entity.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class) // CreatedDate, LastModifiedDate어노테이션 사용 가능
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    private String id;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal expense;

    @OneToMany(mappedBy = "product")
    private List<ProductTransaction> productTransactions;

    @Transient // 임시적인 필드, DB에 저장안됨
    private List<String> transactions;

    @CreatedDate // 생성될 때 자동으로 생성됨.
    @Column(updatable = false)
    private Date createdAt;

    @LastModifiedDate // 수정될 때 자동으로 기입됨.
    private Date updatedAt;
}
