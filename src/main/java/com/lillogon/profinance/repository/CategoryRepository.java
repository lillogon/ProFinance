package com.lillogon.profinance.repository;

import com.lillogon.profinance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByDescription(String description);
    Optional<Category> findByIdAndActiveTrue(UUID id);
}
