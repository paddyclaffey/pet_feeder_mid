package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PetJpaRepository extends JpaRepository<Pet, Long> {
//    List<Pet> findPetsByUserId(Long userId);

//    @Query("SELECT p FROM PetEntity p JOIN p.caretakers c WHERE c.user.id = :userId")
//    List<PetEntity> findByUserId(@Param("userId") Long userId);
}
