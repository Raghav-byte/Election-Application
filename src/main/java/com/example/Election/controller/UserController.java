package com.example.Election.controller;

import com.example.Election.models.User;
import com.example.Election.models.User;
import com.example.Election.service.UserServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @ApiOperation("Create User")
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }

    @ApiOperation("Show all users")
    @GetMapping("/showAllUsers")
    public List<User> showAllUser() {
        return userServices.showAllUser();
    }

    @ApiOperation("Show one user")
    @GetMapping("/{userId}")
    public Optional<User> showOneUser(@PathVariable UUID userId) {
        return userServices.showOneUser(userId);
    }

    @ApiOperation("Deletes user")
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable UUID userId) {
        return userServices.deleteUser(userId);
    }

    @ApiOperation("Updates user")
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userServices.updateUser(user);

    }
}
