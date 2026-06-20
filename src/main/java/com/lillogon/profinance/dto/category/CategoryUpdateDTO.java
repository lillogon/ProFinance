package com.lillogon.profinance.dto.category;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryUpdateDTO(
        @Size(max = 30, message = "Description must have at most 30 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Description must contain only letters and numbers.")
        String description,
        Boolean active
) {
}
