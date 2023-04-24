package com.claffey.petminder.model.entity;

public class UserWithToken extends User {

    private String token;
    public UserWithToken(User user, String token) {
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
    }


}
