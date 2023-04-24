package com.claffey.petminder.controller;

import com.claffey.petminder.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<com.claffey.petminder.model.entity.Pet>> getPets() {
        return ResponseEntity.ok().body(petService.getPets());
    }

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody com.claffey.petminder.model.entity.Pet pet, Authentication authentication) {
        com.claffey.petminder.model.entity.Pet createdPet = petService.createPet(pet, authentication.getName());
        URI uri = URI.create("/pet/" + createdPet.getId());
        return ResponseEntity.created(uri).build();
    }

}