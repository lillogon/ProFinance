package com.lillogon.pro_finance.domain.transactionLog;

import com.lillogon.pro_finance.domain.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "transaction_log")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLog {
    @Id
    private UUID id;

    @Column(nullable = false)
    private Integer installments;
    @Column(name = "event_type", length = 2)
    private String eventType;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}