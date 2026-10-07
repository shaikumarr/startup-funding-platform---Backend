package com.gyf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyf.entity.User;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findFirstByEmailIgnoreCase(String email);

    Optional<User> findFirstByNameIgnoreCase(String name);

    List<User> findByRoleIgnoreCase(String role);

    List<User> findTop10ByOrderByCreatedDateDesc();
}