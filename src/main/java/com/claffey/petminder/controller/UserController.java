package com.claffey.petminder.controller;


import com.claffey.petminder.model.dto.RoleDTO;
import com.claffey.petminder.model.entity.Pet;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.service.PetService;
import com.claffey.petminder.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;
    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @GetMapping("/pet")
    public ResponseEntity<List<Pet>> getUsersPet() {
        return ResponseEntity.ok().body(petService.getPetsForUser());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }


    @PostMapping("/{username}/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @RequestBody RoleDTO request) {
        User user = userService.addRoleToUser(username, request.getRoleName());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           try {
               Map<String, String> tokenMap = userService.refreshToken(authorizationHeader, request.getRequestURL().toString());
               response.addHeader("access_token", tokenMap.get("access_token"));
               response.addHeader("refresh_token", tokenMap.get("refresh_token"));
           }
           catch (Exception e) {
               log.error(String.format("Error refresh token: %s", authorizationHeader), e);
               response.setStatus(FORBIDDEN.value());
               Map<String, String> error = new HashMap<>();
               error.put("errorMessage", e.getMessage());
               response.setContentType(APPLICATION_JSON_VALUE);
               new ObjectMapper().writeValue(response.getOutputStream(), error);
           }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
