package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
