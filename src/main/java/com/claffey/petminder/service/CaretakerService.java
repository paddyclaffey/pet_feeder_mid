package com.claffey.petminder.service;

import com.claffey.petminder.model.entity.CaretakerKey;
import com.claffey.petminder.model.entity.Pet;
import org.springframework.security.core.userdetails.UserDetails;

public interface CaretakerService {

    CaretakerKey create(Pet pet, UserDetails userDetails);
}