package com.claffey.petminder.service.impl;

import com.claffey.petminder.model.entity.Caretaker;
import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.repository.PetJpaRepository;
import com.claffey.petminder.service.PetService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    private final PetJpaRepository petRepository;

    private final CaretakerServiceImpl caretakerService;

    private final UserServiceImpl userService;

    public PetServiceImpl(PetJpaRepository petRepository, CaretakerServiceImpl caretakerService, UserServiceImpl userService) {
        this.petRepository = petRepository;
        this.caretakerService = caretakerService;
        this.userService = userService;
    }

    public List<Pet> getPets() {
        List<Caretaker> caretakers = caretakerService.getAllPets(getLoggedInUser());
        List<Pet> pets = new ArrayList<>();

        caretakers.forEach(caretaker -> {
            pets.add(caretaker.getPet());
        });

        return pets;
    }

    @Override
    public List<User> getAllCaretakers(Pet pet) {
        List<User> users = new ArrayList<>();

        if (isUserCaretaker(getLoggedInUser(), pet)) {

            List<Caretaker> caretakers = caretakerService.getAllCaretakers(pet);
            caretakers.forEach(caretaker -> {
                users.add(caretaker.getUser());
            });
        } else {
            throw new RuntimeException("User does not have permission to view this pet.");
        }

        return users;
    }

    public Pet getPet(Long id) {
        Pet pet = petRepository.findById(id).orElse(null);

        if (pet == null) {
            throw new RuntimeException("No pet found.");

        }

        return pet;
    }

    public Pet createPet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        caretakerService.addAdminRelationship(getLoggedInUser(), pet);
        return savedPet;
    }

    public Pet updatePet(Pet pet) {
        Pet existingPet = getPet(pet.getId());

        if (!caretakerService.isUserAdminOfPet(getLoggedInUser(), pet)) {
            throw new RuntimeException("User is not the owner of this pet.");
        }

        existingPet.setName(pet.getName());
        existingPet.setBreed(pet.getBreed());
        existingPet.setType(pet.getType());
        existingPet.setDob(pet.getDob());
        existingPet.setColour(pet.getColour());
        existingPet.setSize(pet.getSize());
        return petRepository.save(existingPet);
    }

    public void deletePet(Long id, User user) {
        Pet existingPet = petRepository.findById(id).orElse(null);
        if (existingPet == null) {
            throw new RuntimeException("No pet found.");
        }

        if (caretakerService.isUserAdminOfPet(user, existingPet)) {
            petRepository.deleteById(id);
        } else {
            throw new RuntimeException("User is not the owner of this pet.");
        }
    }

    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }

    private boolean isUserCaretaker(User user, Pet pet) {
        return caretakerService.isUserCaretakerOfPet(user, pet) || caretakerService.isUserAdminOfPet(user, pet);
    }

}
