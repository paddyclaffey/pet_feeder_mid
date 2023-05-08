package com.claffey.petminder.service.impl;

import com.claffey.petminder.model.entity.Caretaker;
import com.claffey.petminder.model.entity.CaretakerKey;
import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.repository.CaretakerJpaRepository;
import com.claffey.petminder.service.CaretakerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CaretakerServiceImpl implements CaretakerService {

    private final CaretakerJpaRepository caretakerRepository;

    public CaretakerServiceImpl(CaretakerJpaRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    @Override
    public CaretakerKey create(Pet pet, User user) {
        return null;
    }


    @Override
    public boolean isUserAdminOfPet(User user, Pet pet) {
        if (pet == null) {
            throw new RuntimeException("Pet not found.");
        }
        return caretakerRepository.existsByUserAndPetAndAdminIsTrue(user, pet);
    }

    @Override
    public boolean isUserCaretakerOfPet(User user, Pet pet) {
        return caretakerRepository.existsByUserAndPetAndAdminIsFalse(user, pet);
    }

    @Override
    public Caretaker addAdminRelationship(User user, Pet pet) {
        // Check if the user is already an admin of the pet
        Optional<Caretaker> existingCaretaker = caretakerRepository.findByUserAndPet(user, pet);
        if (existingCaretaker.isPresent()) {
            Caretaker caretaker = existingCaretaker.get();
            if (caretaker.getAdmin()) {
                throw new RuntimeException("User is already an admin of this pet.");
            } else {
                caretaker.setAdmin(true);
                return caretakerRepository.save(caretaker);
            }
        }

        // Create a new admin relationship
        Caretaker newCaretaker = new Caretaker();
        newCaretaker.setUser(user);
        newCaretaker.setPet(pet);
        newCaretaker.setAdmin(true);
        return caretakerRepository.save(newCaretaker);
    }

    @Override
    public List<Caretaker> getAllCaretakers(Pet pet) {
        return caretakerRepository.findByPet(pet);
    }


    @Override
    @Transactional
    public List<Caretaker> getAllPets(User user) {
        return caretakerRepository.findByUser(user);
    }

    public List<Caretaker> getAllCaretakers(User user) {
        return caretakerRepository.findByUser(user);
    }


    public List<Long> getAllPetIds(User user) {
        return caretakerRepository.findByUser(user).stream()
                .map(Caretaker::getPet) // Map to a stream of Pet objects
                .map(Pet::getId) // Map to a stream of pet IDs (Long)
                .collect(Collectors.toList());
    }
}
