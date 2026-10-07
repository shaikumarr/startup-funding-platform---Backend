package com.gyf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyf.entity.InvestmentInterest;

@Repository
public interface InvestmentInterestRepository
        extends JpaRepository<InvestmentInterest, Long> {

    List<InvestmentInterest> findTop10ByOrderByCreatedDateDesc();

    List<InvestmentInterest> findByStartupId(Long startupId);

    List<InvestmentInterest> findByInvestorId(Long investorId);
}