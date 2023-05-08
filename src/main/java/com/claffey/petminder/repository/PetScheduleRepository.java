package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.PetSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PetScheduleRepository extends JpaRepository<PetSchedule, Long> {

    List<PetSchedule> findByPet(Pet pet);
}
