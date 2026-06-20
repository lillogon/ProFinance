package com.lillogon.profinance.repository;

import com.lillogon.profinance.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartyRepository extends JpaRepository<Party, UUID> {
    Optional<Party> findByDocumentNumber(String documentNumber);
}
