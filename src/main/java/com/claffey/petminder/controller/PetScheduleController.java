package com.claffey.petminder.controller;

import com.claffey.petminder.model.entity.PetSchedule;
import com.claffey.petminder.model.mapper.PetScheduleMapper;
import com.claffey.petminder.model.request.PetScheduleRequest;
import com.claffey.petminder.service.PetScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pet-schedule")
public class PetScheduleController {

    private final PetScheduleMapper petScheduleMapper;
    private final PetScheduleService petScheduleService;

    public PetScheduleController(PetScheduleMapper petScheduleMapper, PetScheduleService petScheduleService) {
        this.petScheduleMapper = petScheduleMapper;
        this.petScheduleService = petScheduleService;
    }

    @PostMapping
    public ResponseEntity<?> createPetSchedule(@RequestBody PetScheduleRequest petScheduleRequest) {
        PetSchedule petSchedule = petScheduleMapper.toPetSchedule(petScheduleRequest);
        PetSchedule createdPetSchedule = petScheduleService.save(petSchedule);
        URI uri = URI.create("/petSchedule/" + createdPetSchedule.getId());
        return ResponseEntity.created(uri).build();
    }

//    @PutMapping
//    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {
//        Pet createdPet = petService.updatePet(pet);
//        return ResponseEntity.ok().body(createdPet);
//    }
//
//    @DeleteMapping("/{petId}")
//    public ResponseEntity<?> deletePet(@PathVariable long petId) {
//        return ResponseEntity.ok().body(petService.deletePet(petId));
//    }

}