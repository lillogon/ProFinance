package com.lillogon.profinance.entity.converter;

import com.lillogon.profinance.enums.TransactionType;
import jakarta.persistence.Converter;

@Converter
public class TransactionTypeConverter extends CodeEnumConverter<TransactionType> {
    public TransactionTypeConverter() {
        super(TransactionType.class);
    }
}
