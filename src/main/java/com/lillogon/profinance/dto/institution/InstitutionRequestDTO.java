package com.lillogon.profinance.dto.institution;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InstitutionRequestDTO(
        @NotBlank(message = "Description is required")
        @Size(max = 200, message = "Description must have at most 200 characters")
        String description,
        Boolean isDefault,
        Boolean active
) {
}
