package com.lillogon.profinance.dto.institution;

import java.time.OffsetDateTime;
import java.util.UUID;

public record InstitutionResponseDTO(
        UUID id,
        String description,
        Boolean isDefault,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        OffsetDateTime blockedAt,
        UUID previousDefaultInstitutionId
) {
}
