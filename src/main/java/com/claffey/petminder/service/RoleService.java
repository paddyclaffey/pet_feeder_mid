package com.claffey.petminder.service;

import com.claffey.petminder.model.entity.RoleEntity;

public interface RoleService {

    RoleEntity save(RoleEntity roleEntity);

    RoleEntity findByName(String roleName);

}
