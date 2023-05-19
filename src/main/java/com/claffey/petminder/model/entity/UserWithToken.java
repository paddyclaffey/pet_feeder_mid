package com.claffey.petminder.model.entity;

import lombok.Data;

@Data
public class UserWithToken extends User {

    private String token;

    private boolean isConnected;

    public UserWithToken(User user, String token, boolean isConnected) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setAddress(user.getAddress());
        this.setDob(user.getDob());
        this.setRoles(user.getRoles());
        this.token = token;
        this.isConnected = isConnected;
    }


}
