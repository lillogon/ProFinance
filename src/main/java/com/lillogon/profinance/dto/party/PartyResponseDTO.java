package com.lillogon.profinance.dto.party;

import com.lillogon.profinance.dto.category.CategoryResponseDTO;
import com.lillogon.profinance.enums.PersonType;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PartyResponseDTO(
        UUID id,
        String description,
        String documentNumber,
        PersonType personType,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        OffsetDateTime blockedAt,
        CategoryResponseDTO category
) {
}
