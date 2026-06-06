package com.lillogon.profinance.enums;

import lombok.Getter;

@Getter
public enum EventType implements CodeEnum{

    PAYMENT("PY", "Payment"),
    CANCELLATION("CA", "Cancellation"),
    REVERSAL("RV", "Reversal");

    private final String code;
    private final String description;

    EventType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
