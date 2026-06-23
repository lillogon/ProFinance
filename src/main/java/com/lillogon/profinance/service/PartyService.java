package com.lillogon.profinance.service;

import com.lillogon.profinance.dto.party.PartyRequestDTO;
import com.lillogon.profinance.dto.party.PartyResponseDTO;
import com.lillogon.profinance.dto.party.PartyUpdateDTO;
import com.lillogon.profinance.entity.Category;
import com.lillogon.profinance.entity.Party;
import com.lillogon.profinance.exception.BusinessException;
import com.lillogon.profinance.mapper.PartyMapper;
import com.lillogon.profinance.repository.CategoryRepository;
import com.lillogon.profinance.repository.PartyRepository;
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
public class PartyService {

    private final PartyRepository repository;
    private final PartyMapper mapper;

    private final CategoryRepository categoryRepository;

    //Register party
    public PartyResponseDTO createParty(PartyRequestDTO dto) {
        String description = dto.description().trim();

        Party entity = mapper.toEntity(dto);
        entity.setDescription(description);

        if (dto.documentNumber() != null) {
            String documentNumber = dto.documentNumber().replaceAll("[^0-9]", "");

            if (repository.findByDocumentNumber(documentNumber).isPresent()) {
                throw new BusinessException("Document number already registered.");
            }

            entity.setDocumentNumber(documentNumber);
        }

        if (dto.categoryId() != null) {
            Category category = getCategory(dto.categoryId());
            entity.setCategory(category);
        }

        if (dto.active() != null && !dto.active()) {
            entity.setBlockedAt(OffsetDateTime.now());
        }

        Party saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    //Get all parties
    public Page<PartyResponseDTO> getParties(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Party> result = repository.findAll(pageable);

        return result.map(mapper::toResponse);
    }

    //Get party by id
    public PartyResponseDTO getPartyById(UUID id) {
        return mapper.toResponse(getParty(id));
    }

    //UPDATE party
    public PartyResponseDTO putParty(UUID id, PartyUpdateDTO dto) {
        Party entity = getParty(id);

        if (dto.description() != null) {
            String description = dto.description().trim();
            entity.setDescription(description);
        }

        if (dto.documentNumber() != null) {
            String documentNumber = dto.documentNumber().replaceAll("[^0-9]", "");

            if (!documentNumber.equals(entity.getDocumentNumber())) {
                if (repository.findByDocumentNumber(documentNumber).isPresent()) {
                    throw new BusinessException("Document number already registered.");
                }

                entity.setDocumentNumber(documentNumber);
            }
        }

        if (dto.personType() != null && !dto.personType().equals(entity.getPersonType())) {
            entity.setPersonType(dto.personType());
        }

        if (dto.categoryId() != null &&
                (entity.getCategory() == null || !dto.categoryId().equals(entity.getCategory().getId()))) {

            Category category = getCategory(dto.categoryId());
            entity.setCategory(category);
        }

        if (dto.active() != null && !dto.active().equals(entity.getActive())) {
            entity.setActive(dto.active());

            if (!dto.active()) {
                entity.setBlockedAt(OffsetDateTime.now());
            } else {
                entity.setBlockedAt(null);
            }
        }

        Party saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    //Functions

    //Get Party by ID
    private Party getParty(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Party not found."));
    }

    //Get Category by ID
    private Category getCategory(UUID categoryId) {
        return categoryRepository.findByIdAndActiveTrue(categoryId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Category not found."));
    }
}
