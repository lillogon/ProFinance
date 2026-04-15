package com.lillogon.pro_finance.domain.party;

import com.lillogon.pro_finance.domain.category.CategorySimpleDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record PartyResponseDTO(UUID id, String description, String documentNumber, String personType, Boolean active, LocalDateTime createdAt, LocalDateTime blockedAt, LocalDateTime updatedAt, CategorySimpleDTO category) {
}
