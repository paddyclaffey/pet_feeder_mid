package com.claffey.petminder.controller;


import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@Slf4j
public class Public {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.register(user));
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }
    @GetMapping("/dog-walking")
    public ResponseEntity<JsonNode> getDogWalking() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/dog-walking.json");
            jsonNode = objectMapper.readTree(inputStream);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(jsonNode);
    }
}
