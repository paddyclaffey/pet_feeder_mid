package com.claffey.petminder.service.impl;

import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.repository.PetJpaRepository;
import com.claffey.petminder.service.PetService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    private final PetJpaRepository petRepository;
    private final UserServiceImpl userService;

    public PetServiceImpl(PetJpaRepository petRepository, UserServiceImpl userService) {
        this.petRepository = petRepository;
        this.userService = userService;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsForUser() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pet> getPets() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        // Save the pet to the database
        return petRepository.findAll();
    }

    public Pet createPet(Pet pet, String username) {

        User user = userService.findByUsername(username);
//        pet.setType(PetType.DOG);
        Pet savedPet = petRepository.save(pet);

        // Create a caretaker relationship between the user and the new pet
//        CaretakerEntity caretaker = new CaretakerEntity();
//        caretaker.setAdmin(false);
//        caretaker.setPet(pet);
//        caretaker.setUser(user);

        return savedPet;

    }

    public Pet updatePet(Long id, Pet pet) {
        Pet existingPet = petRepository.findById(id).orElse(null);
        if (existingPet == null) {
            return null;
        }
        existingPet.setName(pet.getName());
//        existingPet.setBreed(petEntity.getBreed());
        existingPet.setType(pet.getType());
        existingPet.setDob(pet.getDob());
//        existingPet.setColour(petEntity.getColour());
//        existingPet.setSize(petEntity.getSize());
        return petRepository.save(existingPet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
