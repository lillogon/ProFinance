package com.lillogon.profinance.dto.account;

import com.lillogon.profinance.dto.category.CategoryResponseDTO;
import com.lillogon.profinance.dto.institution.InstitutionResponseDTO;
import com.lillogon.profinance.dto.party.PartyResponseDTO;
import com.lillogon.profinance.enums.AccountStatus;
import com.lillogon.profinance.enums.PaymentMethod;
import com.lillogon.profinance.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record AccountResponseDTO(
        UUID id,
        TransactionType transactionType,
        AccountStatus accountStatus,
        PaymentMethod paymentMethod,
        Integer installments,
        BigDecimal totalAmount,
        BigDecimal discountAmount,
        BigDecimal amount,
        OffsetDateTime createdAt,
        OffsetDateTime settledAt,
        OffsetDateTime canceledAt,
        OffsetDateTime updatedAt,
        InstitutionResponseDTO institution,
        PartyResponseDTO party,
        CategoryResponseDTO category
) {
}
