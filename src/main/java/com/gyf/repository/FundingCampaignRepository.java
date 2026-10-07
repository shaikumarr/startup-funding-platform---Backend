package com.gyf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyf.entity.FundingCampaign;

@Repository
public interface FundingCampaignRepository
        extends JpaRepository<FundingCampaign, Long> {

    long countByStatus(String status);

    List<FundingCampaign> findTop10ByOrderByCreatedDateDesc();

    List<FundingCampaign> findByStatusIgnoreCase(String status);
}