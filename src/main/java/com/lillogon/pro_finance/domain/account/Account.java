package com.lillogon.pro_finance.domain.account;

import com.lillogon.pro_finance.domain.institution.Institution;
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
    @GeneratedValue
    private UUID id;

    private String transactionType;
    private String status;
    private String paymentMethod;
    private String installments;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal amount;

    private LocalDateTime issuedAt;
    private LocalDateTime settledAt;
    private LocalDateTime canceledAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution financialInstitution;
}