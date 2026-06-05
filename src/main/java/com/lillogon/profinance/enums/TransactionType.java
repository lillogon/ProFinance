package com.lillogon.profinance.enums;

import lombok.Getter;

@Getter
public enum TransactionType implements CodeEnum {

    INCOME("IN", "Income"),
    EXPENSE("EX", "Expense");

    private final String code;
    private final String description;

    TransactionType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}