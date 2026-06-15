package com.lillogon.profinance.enums;

import lombok.Getter;

@Getter
public enum AccountStatus implements CodeEnum {

    OPEN("OP", "Open"),
    PAID("PD", "Paid"),
    OVERDUE("OD", "Overdue"),
    CANCELED("CA", "Canceled");

    private final String code;
    private final String description;

    AccountStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
}