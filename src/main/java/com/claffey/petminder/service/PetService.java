package com.claffey.petminder.service;

import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;

import java.util.List;

public interface PetService {

    List<Pet> getPets();

    Pet getPet(Long id);

    List<User> getAllCaretakers(Pet pet);

    Pet createPet(Pet pet);

    Pet updatePet(Pet pet);

    long deletePet(Long id);
}