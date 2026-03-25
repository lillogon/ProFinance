package com.lillogon.pro_finance.repositories;

import com.lillogon.pro_finance.domain.transactionLog.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionLogRepository extends JpaRepository <TransactionLog, UUID> {
}