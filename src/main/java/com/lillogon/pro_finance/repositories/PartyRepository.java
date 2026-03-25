package com.lillogon.pro_finance.repositories;

import com.lillogon.pro_finance.domain.party.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartyRepository extends JpaRepository <Party, UUID> {
}