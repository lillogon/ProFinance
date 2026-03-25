package com.lillogon.pro_finance.domain.institution;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue
    private UUID id;

    private String description;
    private Boolean isDefault;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime blockedAt;
    private LocalDateTime updatedAt;
}