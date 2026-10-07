package com.gyf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyf.entity.Startup;

@Repository
public interface StartupRepository
        extends JpaRepository<Startup, Long> {

    long countByStatus(String status);

    List<Startup> findTop10ByOrderByCreatedDateDesc();

    List<Startup> findByStatusIgnoreCase(String status);
}