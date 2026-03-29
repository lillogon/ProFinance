package com.lillogon.pro_finance.domain.account;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.institution.Institution;
import com.lillogon.pro_finance.domain.party.Party;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "account")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private UUID id;

    @Column(name = "transaction_type", nullable = false, length = 1)
    private String transactionType;
    @Column(name = "status", nullable = false, length = 2)
    private String status;
    @Column(name = "payment_method", nullable = false, length = 2)
    private String paymentMethod;
    private Integer installments;
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;
    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "settled_at")
    private LocalDateTime settledAt;
    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}