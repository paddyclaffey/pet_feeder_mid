package com.claffey.petminder.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@Slf4j
public class Public {

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
