package com.lillogon.profinance.entity;

import com.lillogon.profinance.entity.converter.PersonTypeConverter;
import com.lillogon.profinance.enums.PersonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "party")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "document_number", length = 14)
    private String documentNumber;

    @Convert(converter = PersonTypeConverter.class)
    @Column(name = "person_type", length = 2)
    private PersonType personType;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Generated(event = EventType.INSERT)
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "blocked_at")
    private OffsetDateTime blockedAt;

    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
