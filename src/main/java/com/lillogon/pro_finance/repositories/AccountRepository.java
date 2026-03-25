package com.lillogon.pro_finance.repositories;

import com.lillogon.pro_finance.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository <Account, UUID> {
}