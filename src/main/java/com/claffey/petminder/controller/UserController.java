package com.claffey.petminder.controller;


import com.claffey.petminder.model.entity.CompletePet;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.service.PetService;
import com.claffey.petminder.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;
    private final PetService petService;

    @GetMapping("username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }


    @GetMapping("/pet")
    public ResponseEntity<List<CompletePet>> getPet() {
        return ResponseEntity.ok().body(petService.getPets());
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
