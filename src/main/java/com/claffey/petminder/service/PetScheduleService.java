package com.claffey.petminder.service;

import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.PetSchedule;

import java.util.List;

public interface PetScheduleService {

    List<PetSchedule> get(Pet pet);

    PetSchedule getSchedule(Long petId);


    PetSchedule save(PetSchedule petSchedule);

    PetSchedule update(PetSchedule petSchedule);

    PetSchedule disable(PetSchedule petSchedule);

    Long delete(Long petScheduleId);

}
