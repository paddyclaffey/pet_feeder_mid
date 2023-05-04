package com.claffey.petminder.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.claffey.petminder.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<?> register(User user);
    User save(User user);
    User addRoleToUser(String username, String roleName);

    User findByUsername(String id);

    User findById(Long id);
    List<User> findAll();
    Map<String,String> refreshToken(String authorizationHeader, String issuer) throws BadJOSEException, ParseException, JOSEException;

    ResponseEntity<?> confirmEmail(String confirmationToken);

    ResponseEntity<?> remove(Long userId);

    public User addUser(User user);
//    public List<UserEntity> getAllUsers();
//    public UserEntity updateUser(Integer userId, UserEntity user);
//    public String deleteUser(Integer userId);


}
