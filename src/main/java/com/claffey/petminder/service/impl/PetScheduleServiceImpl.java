package com.claffey.petminder.service.impl;

import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.PetSchedule;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.repository.PetJpaRepository;
import com.claffey.petminder.repository.PetScheduleRepository;
import com.claffey.petminder.service.PetScheduleService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetScheduleServiceImpl implements PetScheduleService {

    private final PetScheduleRepository petScheduleRepository;

    private final PetJpaRepository petJpaRepository;

    private final CaretakerServiceImpl caretakerService;

    private final UserServiceImpl userService;


    public PetScheduleServiceImpl(PetScheduleRepository petScheduleRepository, PetJpaRepository petJpaRepository, CaretakerServiceImpl caretakerService, UserServiceImpl userService) {
        this.petScheduleRepository = petScheduleRepository;
        this.petJpaRepository = petJpaRepository;
        this.caretakerService = caretakerService;
        this.userService = userService;
    }

    @Override
    public PetSchedule save(PetSchedule petSchedule) {
        Pet pet = petJpaRepository.findById(petSchedule.getPetId()).orElse(null);

        if (pet == null) {
            throw new RuntimeException("No Pet found.");
        }

        if (!caretakerService.isUserAdminOfPet(userService.getLoggedInUser(), pet)) {
            throw new RuntimeException("User is not the owner of this pet.");
        }

        return petScheduleRepository.save(petSchedule);
    }

    @Override
    public List<PetSchedule> get(Pet pet) {
        // Set the fetched schedules to the pet object
        return petScheduleRepository.findByPet(pet);
    }

    @Override
    public PetSchedule getSchedule(Long petId) {
        // Set the fetched schedules to the pet object
        return petScheduleRepository.findById(petId).orElse(null);
    }


    @Override
    public PetSchedule update(PetSchedule petSchedule) {
        return null;
    }

    @Override
    public PetSchedule disable(PetSchedule petSchedule) {
        return null;
    }

    @Override
    public Long delete(Long petScheduleId) {

        PetSchedule petSchedule = getSchedule(petScheduleId);
        if (petSchedule == null) {
            throw new RuntimeException("No pet schedule found.");
        }

        if (caretakerService.isUserAdminOfPet(userService.getLoggedInUser(), petSchedule.getPet())) {
            petScheduleRepository.deleteById(petScheduleId);
        } else {
            throw new RuntimeException("User is not the owner of this pet.");
        }

        return petScheduleId;
    }

}
