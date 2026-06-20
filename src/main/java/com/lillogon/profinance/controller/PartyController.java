package com.lillogon.profinance.controller;

import com.lillogon.profinance.dto.party.PartyRequestDTO;
import com.lillogon.profinance.dto.party.PartyResponseDTO;
import com.lillogon.profinance.dto.party.PartyUpdateDTO;
import com.lillogon.profinance.service.PartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/parties")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService service;

    @PostMapping
    public ResponseEntity<PartyResponseDTO> createParty(@RequestBody @Valid PartyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createParty(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PartyResponseDTO>> getParties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size
    ) {
        return ResponseEntity.ok(service.getParties(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyResponseDTO> getPartyById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getPartyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyResponseDTO> putParty(@PathVariable UUID id, @RequestBody @Valid PartyUpdateDTO dto) {
        return ResponseEntity.ok(service.putParty(id, dto));
    }
}
