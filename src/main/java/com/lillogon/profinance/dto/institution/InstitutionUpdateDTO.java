package com.lillogon.profinance.dto.institution;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InstitutionUpdateDTO(
        @Size(max = 200, message = "Description must have at most 200 characters")
        @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Description must contain only letters and numbers.")
        String description,
        Boolean isDefault,
        Boolean active
) {
}
