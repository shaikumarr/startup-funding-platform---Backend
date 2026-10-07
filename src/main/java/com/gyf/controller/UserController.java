package com.gyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyf.dto.LoginRequest;
import com.gyf.dto.UserRequest;
import com.gyf.entity.User;
import com.gyf.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ==========================================
    // REGISTER
    // POST /api/users/register
    // ==========================================

    @PostMapping("/register")
    public User register(
            @RequestBody UserRequest request) {

        return userService.register(request);
    }

    // ==========================================
    // LOGIN
    // POST /api/users/login
    // ==========================================

    @PostMapping("/login")
    public User login(
            @RequestBody LoginRequest request) {

        return userService.login(request);
    }

    // ==========================================
    // GET ALL USERS
    // GET /api/users
    // ==========================================

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // ==========================================
    // GET USER BY ID
    // GET /api/users/{id}
    // ==========================================

    @GetMapping("/{id:\\d+}")
    public User getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id);
    }

    // ==========================================
    // DELETE USER
    // DELETE /api/users/{id}
    // ==========================================

    @DeleteMapping("/{id:\\d+}")
    public String deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return "User Deleted Successfully";
    }

    // ==========================================
    // UPDATE USER
    // PUT /api/users/{id}
    // ==========================================

    @PutMapping("/{id:\\d+}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request) {

        return userService.updateUser(
                id,
                request);
    }
}