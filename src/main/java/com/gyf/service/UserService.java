package com.gyf.service;

import java.util.List;

import com.gyf.dto.LoginRequest;
import com.gyf.dto.UserRequest;
import com.gyf.entity.User;

public interface UserService {

    User register(UserRequest request);

    User login(LoginRequest request);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User updateUser(Long id, UserRequest request);
}