package com.lillogon.profinance.service;

import com.lillogon.profinance.dto.institution.InstitutionRequestDTO;
import com.lillogon.profinance.dto.institution.InstitutionResponseDTO;
import com.lillogon.profinance.dto.institution.InstitutionUpdateDTO;
import com.lillogon.profinance.entity.Institution;
import com.lillogon.profinance.exception.BusinessException;
import com.lillogon.profinance.mapper.InstitutionMapper;
import com.lillogon.profinance.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository repository;
    private final InstitutionMapper mapper;

    //Register Institution
    public InstitutionResponseDTO createInstitution(InstitutionRequestDTO dto) {
        String description = dto.description().trim();

        if (repository.findByDescription(description).isPresent()) {
            throw new BusinessException("Description already registered.");
        }

        Institution entity = mapper.toEntity(dto);

        if (dto.active() != null && !dto.active()) {
            entity.setBlockedAt(OffsetDateTime.now());
        }

        UUID previousDefaultId = handleDefaultInstitution(dto.isDefault());

        Institution saved = repository.save(entity);
        return mapper.toResponse(saved, previousDefaultId);
    }

    // Get all institutions
    public Page<InstitutionResponseDTO> getInstitutions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Institution> result = repository.findAll(pageable);

        return result.map(mapper::toResponse);
    }

    // Get institution by id
    public InstitutionResponseDTO getInstitutionById(UUID id) {
        return mapper.toResponse(getInstitution(id));
    }

    // UPDATE institution
    public InstitutionResponseDTO putInstitution(UUID id, InstitutionUpdateDTO dto) {
        Institution entity = getInstitution(id);

        if (dto.description() != null) {
            String description = dto.description().trim();

            if (!description.equals(entity.getDescription())) {
                if (repository.findByDescription(description).isPresent()) {
                    throw new BusinessException("Description already registered.");
                }

                entity.setDescription(description);
            }
        }

        if (dto.active() != null && !dto.active().equals(entity.getActive())) {
            entity.setActive(dto.active());

            if (!dto.active()) {
                entity.setBlockedAt(OffsetDateTime.now());
            } else {
                entity.setBlockedAt(null);
            }
        }

        UUID previousDefaultId = null;

        if (dto.isDefault() != null && !dto.isDefault().equals(entity.getIsDefault())) {
            previousDefaultId = handleDefaultInstitution(dto.isDefault());
            entity.setIsDefault(dto.isDefault());
        }

        Institution saved = repository.save(entity);
        return mapper.toResponse(saved, previousDefaultId);
    }

    //Functions

    //Get Institution by ID
    private Institution getInstitution(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Institution not found."));
    }

    // Verify - Is Default flag
    private UUID handleDefaultInstitution(Boolean isDefault) {
        if (isDefault == null || !isDefault) {
            return null;
        }

        return repository.findByIsDefaultTrue()
                .map(current -> {
                    current.setIsDefault(false);
                    repository.save(current);
                    return current.getId();
                })
                .orElse(null);
    }
}
