package com.lillogon.profinance.entity;

import com.lillogon.profinance.entity.converter.AccountStatusConverter;
import com.lillogon.profinance.entity.converter.PaymentMethodConverter;
import com.lillogon.profinance.entity.converter.TransactionTypeConverter;
import com.lillogon.profinance.enums.AccountStatus;
import com.lillogon.profinance.enums.PaymentMethod;
import com.lillogon.profinance.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Convert(converter = TransactionTypeConverter.class)
    @Column(name = "transaction_type", length = 2, nullable = false)
    private TransactionType transactionType;

    @Convert(converter = AccountStatusConverter.class)
    @Column(name = "status", length = 2, nullable = false)
    private AccountStatus accountStatus;

    @Convert(converter = PaymentMethodConverter.class)
    @Column(name = "payment_method", length = 2, nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "installments", nullable = false)
    private Integer installments;

    @Column(name = "total_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "discount_amount", precision = 15, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Generated(event = EventType.INSERT)
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "settled_at")
    private OffsetDateTime settledAt;

    @Column(name = "canceled_at")
    private OffsetDateTime canceledAt;

    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
