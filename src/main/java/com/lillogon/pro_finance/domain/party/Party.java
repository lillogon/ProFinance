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
    @GeneratedValue
    private UUID id;

    private String description;
    private String documentNumber;
    private String personType;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime blockedAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}