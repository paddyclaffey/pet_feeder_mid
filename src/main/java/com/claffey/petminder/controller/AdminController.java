package com.claffey.petminder.controller;


import com.claffey.petminder.model.dto.RoleDTO;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.service.PetService;
import com.claffey.petminder.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
@Role(1)
public class AdminController {

    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping("add/{userId}")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @DeleteMapping("remove/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.remove(userId));
    }


    @PostMapping("/{username}/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @RequestBody RoleDTO request) {
        User user = userService.addRoleToUser(username, request.getRoleName());
        return ResponseEntity.ok(user);
    }

}
