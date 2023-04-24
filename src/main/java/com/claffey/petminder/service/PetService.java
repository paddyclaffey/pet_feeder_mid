package com.claffey.petminder.service;

import com.claffey.petminder.model.entity.Pet;

import java.util.List;

public interface PetService {

    List<Pet> getPets();

    List<Pet> getPetsForUser();

    Pet createPet(Pet pet, String username);
}