package com.claffey.petminder.repository;

import com.claffey.petminder.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
