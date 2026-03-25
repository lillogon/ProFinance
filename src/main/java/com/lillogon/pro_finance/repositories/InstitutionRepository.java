package com.lillogon.pro_finance.repositories;

import com.lillogon.pro_finance.domain.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstitutionRepository extends JpaRepository <Institution, UUID> {
}