package com.lillogon.profinance.enums;

import lombok.Getter;

@Getter
public enum PersonType implements CodeEnum {

    INDIVIDUAL("ID", "Individual"),
    COMPANY("CO", "Company");

    private final String code;
    private final String description;

    PersonType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}