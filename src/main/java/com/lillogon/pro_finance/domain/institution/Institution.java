package com.lillogon.pro_finance.domain.institution;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "institution")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Institution {
    @Id
    private UUID id;

    @Column(nullable = false, length = 200)
    private String description;
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;
    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "blocked_at")
    private LocalDateTime blockedAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}