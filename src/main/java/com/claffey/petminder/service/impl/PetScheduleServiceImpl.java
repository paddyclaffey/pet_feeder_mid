package com.claffey.petminder.service.impl;

import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.PetSchedule;
import com.claffey.petminder.model.entity.User;
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

    private final CaretakerServiceImpl caretakerService;

    private final UserServiceImpl userService;


    public PetScheduleServiceImpl(PetScheduleRepository petScheduleRepository, CaretakerServiceImpl caretakerService,  UserServiceImpl userService) {
        this.petScheduleRepository = petScheduleRepository;
        this.caretakerService = caretakerService;
        this.userService = userService;
    }

    @Override
    public PetSchedule save(PetSchedule petSchedule) {
        if (!caretakerService.isUserAdminOfPet(getLoggedInUser(), petSchedule.getPet())) {
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
    public PetSchedule update(PetSchedule petSchedule) {
        return null;
    }

    @Override
    public PetSchedule disable(PetSchedule petSchedule) {
        return null;
    }

    @Override
    public PetSchedule remove(PetSchedule petSchedule) {
        return null;
    }

    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }

    private boolean isUserCaretaker(User user, Pet pet) {
        return caretakerService.isUserCaretakerOfPet(user, pet) || caretakerService.isUserAdminOfPet(user, pet);
    }

}
