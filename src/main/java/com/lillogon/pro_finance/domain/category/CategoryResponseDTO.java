package com.lillogon.pro_finance.domain.category;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponseDTO(UUID id, String description, Boolean active, LocalDateTime createdAt, LocalDateTime blockedAt, LocalDateTime updatedAt) {
}
