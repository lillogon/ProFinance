package com.lillogon.pro_finance.domain.party;

import com.lillogon.pro_finance.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "party")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Party {
    @Id
    private UUID id;

    @Column(nullable = false, length = 200)
    private String description;
    @Column(name = "document_number", length = 14)
    private String documentNumber;
    @Column(name = "person_type", length = 1)
    private String personType;
    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "blocked_at")
    private LocalDateTime blockedAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}