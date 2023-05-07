package com.claffey.petminder.controller;

import com.claffey.petminder.model.entity.Pet;
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
    public ResponseEntity<List<Pet>> getPets() {
        return ResponseEntity.ok().body(petService.getPets());
    }

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet);
        URI uri = URI.create("/pet/" + createdPet.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        Pet createdPet = petService.updatePet(pet);
        return ResponseEntity.ok().body(createdPet);
    }

}