package com.lillogon.pro_finance.domain.party;

import com.lillogon.pro_finance.domain.category.CategorySimpleDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record PartyResponseDTO(UUID id, String description, String documentNumber, String personType, Boolean active, LocalDateTime createdAt, LocalDateTime blockedAt, LocalDateTime updatedAt, CategorySimpleDTO category) {
    public static PartyResponseDTO from(Party party){
        CategorySimpleDTO categorySimpleDTO = party.getCategory() != null
                ? new CategorySimpleDTO(party.getCategory().getId(), party.getCategory().getDescription())
                : null;

        return new PartyResponseDTO(
          party.getId(),
          party.getDescription(),
          party.getDocumentNumber(),
          party.getPersonType(),
          party.getActive(),
          party.getCreatedAt(),
          party.getBlockedAt(),
          party.getUpdatedAt(),
          categorySimpleDTO
        );
    }
}
