package com.gyf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyf.entity.Investor;

@Repository
public interface InvestorRepository
        extends JpaRepository<Investor, Long> {

    long countByStatus(String status);

    List<Investor> findByStatusIgnoreCase(String status);

    List<Investor> findTop10ByOrderByCreatedDateDesc();
}