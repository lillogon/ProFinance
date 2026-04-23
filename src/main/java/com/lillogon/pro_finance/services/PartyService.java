package com.lillogon.pro_finance.services;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.party.Party;
import com.lillogon.pro_finance.domain.party.PartyRequestDTO;
import com.lillogon.pro_finance.domain.party.PartyResponseDTO;
import com.lillogon.pro_finance.exceptions.ResourceNotFoundException;
import com.lillogon.pro_finance.repositories.CategoryRepository;
import com.lillogon.pro_finance.repositories.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final CategoryRepository categoryRepository;

    public PartyResponseDTO createParty(PartyRequestDTO data){
        Party newParty = new Party();

        if (data.description() == null || data.description().isBlank()){
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        newParty.setDescription(data.description().toLowerCase().trim());

        String rawDocument = data.documentNumber();
        if (rawDocument != null && !rawDocument.isBlank()){
            String document = rawDocument.replaceAll("\\D", "");
            if (document.length() >= 9 && document.length() <=14){
                newParty.setDocumentNumber(document);
            } else {
                throw new IllegalArgumentException("Document number must have between 9 and 14 digits");
            }
        }

        if (data.personType() != null && !data.personType().isBlank()){
            String type = data.personType().toUpperCase();
            if ("I".equals(type) || "C".equals(type)){
                newParty.setPersonType(type);
            } else {
                throw new IllegalArgumentException("Person type must be 'I' (individual) or 'C' (company)");
            }
        }

        boolean active = data.active() != null ? data.active() : true;
        newParty.setActive(active);

        LocalDateTime now = LocalDateTime.now();
        newParty.setCreatedAt(now);
        newParty.setUpdatedAt(now);
        if (!active){
            newParty.setBlockedAt(now);
        }

        if (data.categoryId() != null) {
            Category category = categoryRepository.findById(data.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
            if (!category.getActive()){
                throw new IllegalArgumentException("Cannot assign an inactive category");
            }
            newParty.setCategory(category);
        }

        partyRepository.save(newParty);
        return PartyResponseDTO.from(newParty);
    }

    public List<PartyResponseDTO> getParties(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return partyRepository.findAll(pageable)
                .getContent().stream()
                .map(PartyResponseDTO::from)
                .toList();
    }

    public PartyResponseDTO getPartyById(UUID id){
        return partyRepository.findById(id)
                .map(PartyResponseDTO::from)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found."));
    }

    public void deleteParty(UUID id) {
        Party party = partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found."));

        partyRepository.delete(party);
    }
}