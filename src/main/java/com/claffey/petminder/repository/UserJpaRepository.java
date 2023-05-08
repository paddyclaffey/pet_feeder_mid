package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmailIgnoreCase(String email);

//    List<Pet> findUsersByPetId(Long petId);
//
//    @Query("SELECT u FROM UserEntity u JOIN u.caretakers c WHERE c.pet.id = :petId")
//    List<UserEntity> findByPetId(@Param("petId") Long petId);
}
