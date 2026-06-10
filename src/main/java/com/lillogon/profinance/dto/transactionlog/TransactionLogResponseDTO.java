package com.lillogon.profinance.dto.transactionlog;

import com.lillogon.profinance.enums.EventType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionLogResponseDTO(
        UUID id,
        Integer installmentNumber,
        EventType eventType,
        BigDecimal amount,
        OffsetDateTime eventDate,
        UUID accountId
) {
}
