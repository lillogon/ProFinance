package com.lillogon.profinance.entity.converter;

import com.lillogon.profinance.enums.CodeEnum;
import jakarta.persistence.AttributeConverter;

public abstract class CodeEnumConverter<T extends Enum<T> & CodeEnum>
        implements AttributeConverter<T, String> {

    private final Class<T> enumClass;

    protected CodeEnumConverter(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null) return null;
        return attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        for (T value : enumClass.getEnumConstants()) {
            if (value.getCode().equals(dbData)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + dbData);
    }
}