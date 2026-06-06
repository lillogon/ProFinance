package com.lillogon.profinance.entity;

import com.lillogon.profinance.entity.converter.EventTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import com.lillogon.profinance.enums.EventType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_log")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "installment_number", nullable = false)
    private Integer installmentNumber;

    @Convert(converter = EventTypeConverter.class)
    @Column(name = "event_type", length = 2)
    private EventType eventType;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Generated(event = org.hibernate.generator.EventType.INSERT)
    @Column(name = "event_date", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
