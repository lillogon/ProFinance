package com.lillogon.profinance.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotBlank(message = "Description is required")
        @Size(max = 30, message = "Description must have at most 30 characters")
        String description,
        Boolean active
) {
}
