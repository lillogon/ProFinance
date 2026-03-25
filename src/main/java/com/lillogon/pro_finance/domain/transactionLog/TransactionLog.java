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
    @GeneratedValue
    private UUID id;

    private String installments;
    private String eventType;
    private BigDecimal amount;
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}