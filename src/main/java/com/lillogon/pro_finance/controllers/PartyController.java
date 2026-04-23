package com.lillogon.pro_finance.controllers;

import com.lillogon.pro_finance.domain.party.PartyRequestDTO;
import com.lillogon.pro_finance.domain.party.PartyResponseDTO;
import com.lillogon.pro_finance.services.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/party")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    @PostMapping
    public ResponseEntity<PartyResponseDTO> create(@RequestBody PartyRequestDTO body){
        return ResponseEntity.ok(partyService.createParty(body));
    }

    @GetMapping
    public ResponseEntity<List<PartyResponseDTO>> getParties(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(partyService.getParties(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyResponseDTO> getPartyById(@PathVariable UUID id){
        return ResponseEntity.ok(partyService.getPartyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParty(@PathVariable UUID id){
        partyService.deleteParty(id);
        return ResponseEntity.ok("Party deleted successfully.");
    }
}
