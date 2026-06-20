package com.lillogon.profinance.repository;

import com.lillogon.profinance.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, UUID> {
    Optional<Institution> findByDescription(String description);
    Optional<Institution> findByIsDefaultTrue();
}
