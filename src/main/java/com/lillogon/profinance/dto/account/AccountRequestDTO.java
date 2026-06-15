package com.lillogon.profinance.dto.account;

import com.lillogon.profinance.enums.AccountStatus;
import com.lillogon.profinance.enums.PaymentMethod;
import com.lillogon.profinance.enums.TransactionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountRequestDTO(
        @NotNull(message = "Transaction type is required")
        TransactionType transactionType,
        @NotNull(message = "Status is required")
        AccountStatus accountStatus,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Installments is required")
        @Min(value = 1, message = "Installments must be at least 1")
        Integer installments,
        @NotNull(message = "Total amount is required")
        @DecimalMin(value = "0.01", message = "Total amount must be greater than 0")
        BigDecimal totalAmount,
        @DecimalMin(value = "0.01", message = "Discount amount must be greater than 0")
        BigDecimal discountAmount,
        @NotNull(message = "Institution is required")
        UUID institutionId,
        @NotNull(message = "Party is required")
        UUID partyId,
        UUID categoryId
) {
}
