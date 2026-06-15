package com.lillogon.profinance.dto.category;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CategoryResponseDTO(
        UUID id,
        String description,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        OffsetDateTime blockedAt
) {
}
