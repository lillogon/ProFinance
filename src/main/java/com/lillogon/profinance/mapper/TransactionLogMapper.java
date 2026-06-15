package com.lillogon.profinance.mapper;

import com.lillogon.profinance.dto.transactionlog.TransactionLogResponseDTO;
import com.lillogon.profinance.entity.TransactionLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionLogMapper {

    public TransactionLogResponseDTO toResponse(TransactionLog transactionLog) {
        return new TransactionLogResponseDTO(
                transactionLog.getId(),
                transactionLog.getInstallmentNumber(),
                transactionLog.getEventType(),
                transactionLog.getAmount(),
                transactionLog.getEventDate(),
                transactionLog.getAccount().getId()
        );
    }

    public List<TransactionLogResponseDTO> toResponseList(List<TransactionLog> logs) {
        return logs.stream()
                .map(this::toResponse)
                .toList();
    }
}
