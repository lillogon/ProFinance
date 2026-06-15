package com.lillogon.profinance.mapper;

import com.lillogon.profinance.dto.institution.InstitutionRequestDTO;
import com.lillogon.profinance.dto.institution.InstitutionResponseDTO;
import com.lillogon.profinance.entity.Institution;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstitutionMapper {

    public InstitutionResponseDTO toResponse(Institution institution) {
        return new InstitutionResponseDTO(
                institution.getId(),
                institution.getDescription(),
                institution.getIsDefault(),
                institution.getActive(),
                institution.getCreatedAt(),
                institution.getUpdatedAt(),
                institution.getBlockedAt()
        );
    }

    public List<InstitutionResponseDTO> toResponseList(List<Institution> institutions) {
        return institutions.stream()
                .map(this::toResponse)
                .toList();
    }

    public Institution toEntity(InstitutionRequestDTO dto) {
        Institution institution = new Institution();
        institution.setDescription(dto.description());
        institution.setIsDefault(dto.isDefault() != null ? dto.isDefault() : false);
        institution.setActive(dto.active() != null ? dto.active() : true);
        return institution;
    }
}
