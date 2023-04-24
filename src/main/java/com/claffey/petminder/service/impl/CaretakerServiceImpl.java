package com.claffey.petminder.service.impl;

import com.claffey.petminder.model.entity.CaretakerKey;
import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.repository.CaretakerJpaRepository;
import com.claffey.petminder.service.CaretakerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaretakerServiceImpl implements CaretakerService {

    private final CaretakerJpaRepository caretakerRepository;

    public CaretakerServiceImpl(CaretakerJpaRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    public CaretakerKey create(Pet pet, User user) {
        return null;
    }

    @Override
    public CaretakerKey create(Pet pet, UserDetails userDetails) {
        return null;
    }
}
