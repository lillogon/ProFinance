package com.lillogon.profinance.controller;

import com.lillogon.profinance.dto.institution.InstitutionRequestDTO;
import com.lillogon.profinance.dto.institution.InstitutionResponseDTO;
import com.lillogon.profinance.dto.institution.InstitutionUpdateDTO;
import com.lillogon.profinance.service.InstitutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService service;

    @PostMapping
    public ResponseEntity<InstitutionResponseDTO> createInstitution(@RequestBody @Valid InstitutionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createInstitution(dto));
    }

    @GetMapping
    public ResponseEntity<Page<InstitutionResponseDTO>> getInstitutions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.getInstitutions(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> getInstitutionById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getInstitutionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> putInstitution(@PathVariable UUID id, @RequestBody @Valid InstitutionUpdateDTO dto) {
        return ResponseEntity.ok(service.putInstitution(id, dto));
    }
}
