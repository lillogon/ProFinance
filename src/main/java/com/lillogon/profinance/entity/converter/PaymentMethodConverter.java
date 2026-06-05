package com.lillogon.profinance.entity.converter;

import com.lillogon.profinance.enums.PaymentMethod;
import jakarta.persistence.Converter;

@Converter
public class PaymentMethodConverter extends CodeEnumConverter<PaymentMethod> {
    public PaymentMethodConverter() {
        super(PaymentMethod.class);
    }
}
