package com.lillogon.pro_finance.controllers;

import com.lillogon.pro_finance.domain.party.Party;
import com.lillogon.pro_finance.domain.party.PartyRequestDTO;
import com.lillogon.pro_finance.domain.party.PartyResponseDTO;
import com.lillogon.pro_finance.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping
    public ResponseEntity<Party> create(@RequestBody PartyRequestDTO body){
        Party newParty = this.partyService.createParty(body);
        return ResponseEntity.ok(newParty);
    }

    @GetMapping
    public ResponseEntity<List<PartyResponseDTO>> getParties(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<PartyResponseDTO> allParties = this.partyService.getParties(page, size);
        return ResponseEntity.ok(allParties);
    }
}
