package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.Caretaker;
import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CaretakerJpaRepository extends JpaRepository<Caretaker, Long> {
    List<Caretaker> findByUser(User user);


    List<Caretaker> findByPet(Pet pet);

    Optional<Caretaker> findByUserAndPet(User user, Pet pet);
    boolean existsByUserAndPetAndAdminIsTrue(User user, Pet pet);

    boolean existsByUserAndPetAndAdminIsFalse(User user, Pet pet);

    List<Caretaker> findByUserId(Long userId);

}
