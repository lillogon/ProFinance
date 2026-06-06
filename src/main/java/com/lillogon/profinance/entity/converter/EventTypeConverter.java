package com.lillogon.profinance.entity.converter;

import com.lillogon.profinance.enums.EventType;
import jakarta.persistence.Converter;

@Converter
public class EventTypeConverter extends CodeEnumConverter<EventType> {
    public EventTypeConverter() {
        super(EventType.class);
    }
}
