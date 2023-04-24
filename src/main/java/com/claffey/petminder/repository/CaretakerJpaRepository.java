package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CaretakerJpaRepository extends JpaRepository<Pet, Long> {
//    List<CaretakerEntity> findByUser(UserEntity user);
}
