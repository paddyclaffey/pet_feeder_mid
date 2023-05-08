package com.claffey.petminder.model.mapper;
import com.claffey.petminder.model.entity.PetSchedule;
import com.claffey.petminder.model.request.PetScheduleRequest;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class PetScheduleMapper {
    public PetSchedule toPetSchedule(PetScheduleRequest request) {
        PetSchedule petSchedule = new PetSchedule();

        petSchedule.setPetId(request.getPetId());
        petSchedule.setCategory(request.getCategory());
        petSchedule.setDescription(request.getDescription());
        petSchedule.setStartTime(request.getStartTime());
        petSchedule.setFrequencyInterval((request.getFrequency().getHours() * 3600) + (request.getFrequency().getMinutes() * 60) );
        petSchedule.setActive(request.isActive());

        return petSchedule;
    }
}
