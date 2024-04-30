package com.septemarch.test.controller;

import com.septemarch.test.dto.SearchDTO;
import com.septemarch.test.entitis.User;
import com.septemarch.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody @Validated User user) {
        userService.createUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Validated User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @PostMapping("/search")
    public List<User> searchUser(@RequestBody SearchDTO searchDTO) {
        return userService.searchUsers(searchDTO);
    }



}
