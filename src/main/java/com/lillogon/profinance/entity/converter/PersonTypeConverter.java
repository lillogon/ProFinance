package com.lillogon.profinance.entity.converter;

import com.lillogon.profinance.enums.PersonType;
import jakarta.persistence.Converter;

@Converter
public class PersonTypeConverter extends CodeEnumConverter<PersonType> {
    public PersonTypeConverter() {
        super(PersonType.class);
    }
}
