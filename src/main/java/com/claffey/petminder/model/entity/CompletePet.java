package com.claffey.petminder.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CompletePet {
    private long id;
    private String name;
    private String type;
    private String breed;
    private String colour;
    private LocalDate dob;
    private String size;
    private Set<User> users;
    private List<PetSchedule> schedules;

    public CompletePet(Pet pet, List<PetSchedule> schedules) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.type = pet.getType();
        this.breed = pet.getBreed();
        this.colour = pet.getColour();
        this.dob = pet.getDob();
        this.size = pet.getSize();
        this.users = pet.getUsers();
        // Set the other fields from the Pet class
        this.schedules = schedules;
    }
}
