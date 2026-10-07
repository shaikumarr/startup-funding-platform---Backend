package com.gyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.LoginRequest;
import com.gyf.dto.UserRequest;
import com.gyf.entity.User;
import com.gyf.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public User register(UserRequest request) {

        if (request == null) {
            throw new RuntimeException(
                    "Registration request cannot be empty");
        }

        String name = request.getName() == null
                ? ""
                : request.getName().trim();

        String email = request.getEmail() == null
                ? ""
                : request.getEmail().trim()
                        .toLowerCase();

        String password = request.getPassword() == null
                ? ""
                : request.getPassword();

        String role = request.getRole() == null
                ? ""
                : request.getRole().trim()
                        .toUpperCase();

        if (name.isEmpty()) {
            throw new RuntimeException(
                    "Name is required");
        }

        if (email.isEmpty()) {
            throw new RuntimeException(
                    "Email is required");
        }

        if (password.isEmpty()) {
            throw new RuntimeException(
                    "Password is required");
        }

        if (password.length() < 6) {
            throw new RuntimeException(
                    "Password must contain at least 6 characters");
        }

        if (!role.equals("STARTUP")
                && !role.equals("INVESTOR")
                && !role.equals("ADMIN")) {

            throw new RuntimeException(
                    "Invalid role. Use STARTUP, INVESTOR or ADMIN");
        }

        if (userRepository
                .findFirstByEmailIgnoreCase(email)
                .isPresent()) {

            throw new RuntimeException(
                    "An account with this email already exists");
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);

        /*
         * Keeping the same authentication design
         * currently used by your backend.
         */
        user.setPassword(password);

        user.setRole(role);
        user.setStatus("ACTIVE");

        User savedUser =
                userRepository.save(user);

        /*
         * Notify administrators.
         *
         * If notification creation itself fails,
         * registration should still be considered
         * successful.
         */
        try {

            notificationService.notifyAdmins(
                    "New User Registered",
                    savedUser.getName()
                            + " has registered on GYF.",
                    "NEW_USER",
                    savedUser.getId(),
                    "/admin/users");

        } catch (Exception notificationError) {

            System.err.println(
                    "Notification error after registration: "
                            + notificationError.getMessage());
        }

        return savedUser;
    }

    @Override
    public User login(LoginRequest request) {

        if (request == null) {
            throw new RuntimeException(
                    "Login request cannot be empty");
        }

        String email = request.getEmail() == null
                ? ""
                : request.getEmail().trim()
                        .toLowerCase();

        String password = request.getPassword() == null
                ? ""
                : request.getPassword();

        if (email.isEmpty()) {
            throw new RuntimeException(
                    "Email is required");
        }

        if (password.isEmpty()) {
            throw new RuntimeException(
                    "Password is required");
        }

        User user =
                userRepository
                        .findFirstByEmailIgnoreCase(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        if (!user.getPassword()
                .equals(password)) {

            throw new RuntimeException(
                    "Invalid password");
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        if (id == null) {
            throw new RuntimeException(
                    "User ID is required");
        }

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));
    }

    @Override
    public void deleteUser(Long id) {

        if (id == null) {
            throw new RuntimeException(
                    "User ID is required");
        }

        if (!userRepository.existsById(id)) {
            throw new RuntimeException(
                    "User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(
            Long id,
            UserRequest request) {

        if (id == null) {
            throw new RuntimeException(
                    "User ID is required");
        }

        if (request == null) {
            throw new RuntimeException(
                    "Update request cannot be empty");
        }

        User user =
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        String name = request.getName() == null
                ? ""
                : request.getName().trim();

        String email = request.getEmail() == null
                ? ""
                : request.getEmail().trim()
                        .toLowerCase();

        String role = request.getRole() == null
                ? ""
                : request.getRole().trim()
                        .toUpperCase();

        if (name.isEmpty()) {
            throw new RuntimeException(
                    "Name is required");
        }

        if (email.isEmpty()) {
            throw new RuntimeException(
                    "Email is required");
        }

        if (!role.equals("STARTUP")
                && !role.equals("INVESTOR")
                && !role.equals("ADMIN")) {

            throw new RuntimeException(
                    "Invalid role");
        }

        /*
         * Don't allow another user to use
         * this email.
         */
        userRepository
                .findFirstByEmailIgnoreCase(email)
                .ifPresent(existing -> {

                    if (!existing.getId()
                            .equals(id)) {

                        throw new RuntimeException(
                                "Another account already uses this email");
                    }
                });

        user.setName(name);
        user.setEmail(email);
        user.setRole(role);

        if (request.getPassword() != null
                && !request.getPassword().isBlank()) {

            user.setPassword(
                    request.getPassword());
        }

        return userRepository.save(user);
    }
}