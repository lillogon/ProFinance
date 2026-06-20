package com.lillogon.profinance.dto.party;

import com.lillogon.profinance.enums.PersonType;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PartyUpdateDTO(
        @Size(max = 200, message = "Description must have at most 200 characters")
        String description,
        @Size(min = 11, max = 14, message = "Document number must be between 11 and 14 characters")
        String documentNumber,
        PersonType personType,
        Boolean active,
        UUID categoryId
) {}
