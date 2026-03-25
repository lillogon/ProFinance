package com.lillogon.pro_finance.repositories;

import com.lillogon.pro_finance.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository <Category, UUID> {
}