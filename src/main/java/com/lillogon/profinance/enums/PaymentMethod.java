package com.lillogon.profinance.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod implements CodeEnum {

    CREDIT_CARD("CC", "Credit Card"),
    DEBIT_CARD("DC", "Debit Card"),
    PIX("PX", "PIX"),
    CASH("CS", "Cash");

    private final String code;
    private final String description;

    PaymentMethod(String code, String description) {
        this.code = code;
        this.description = description;
    }
}