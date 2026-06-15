package com.lillogon.profinance.entity.converter;

import com.lillogon.profinance.enums.AccountStatus;
import jakarta.persistence.Converter;

@Converter
public class AccountStatusConverter extends CodeEnumConverter<AccountStatus> {
    public AccountStatusConverter() {
        super(AccountStatus.class);
    }
}
