package com.claffey.petminder.service;

import com.claffey.petminder.model.entity.Caretaker;
import com.claffey.petminder.model.entity.CaretakerKey;
import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;

import java.util.List;

public interface CaretakerService {

    CaretakerKey create(Pet pet, User user);

    boolean isUserAdminOfPet(User user, Pet pet);

    boolean isUserCaretakerOfPet(User user, Pet pet);

    Caretaker addAdminRelationship(User user, Pet pet);

    List<Caretaker> getAllCaretakers(Pet pet);

    List<Caretaker> getAllPets(User user);
}