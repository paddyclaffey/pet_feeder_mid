package com.claffey.petminder.model.request;

import com.claffey.petminder.model.Frequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetScheduleRequest {

    private long id;
    private long petId;
    private String category;
    private String description;
    private LocalTime startTime;
    private Frequency frequency;
    private boolean active;

}
