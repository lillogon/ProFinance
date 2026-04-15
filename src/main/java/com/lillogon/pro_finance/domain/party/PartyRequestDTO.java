package com.lillogon.pro_finance.domain.party;

import java.util.UUID;

public record PartyRequestDTO(String description, String documentNumber, String personType, Boolean active, UUID categoryId) {
}
