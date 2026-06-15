package com.lillogon.profinance.mapper;

import com.lillogon.profinance.dto.party.PartyRequestDTO;
import com.lillogon.profinance.dto.party.PartyResponseDTO;
import com.lillogon.profinance.entity.Party;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartyMapper {

    private final CategoryMapper categoryMapper;

    public PartyMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public PartyResponseDTO toResponse(Party party) {
        return new PartyResponseDTO(
                party.getId(),
                party.getDescription(),
                party.getDocumentNumber(),
                party.getPersonType(),
                party.getActive(),
                party.getCreatedAt(),
                party.getUpdatedAt(),
                party.getBlockedAt(),
                party.getCategory() != null
                        ? categoryMapper.toResponse(party.getCategory())
                        : null
        );
    }

    public List<PartyResponseDTO> toResponseList(List<Party> parties) {
        return parties.stream()
                .map(this::toResponse)
                .toList();
    }

    public Party toEntity(PartyRequestDTO dto) {
        Party party = new Party();
        party.setDescription(dto.description());
        party.setDocumentNumber(dto.documentNumber());
        party.setPersonType(dto.personType());
        party.setActive(dto.active() != null ? dto.active() : true);
        return party;
    }
}
