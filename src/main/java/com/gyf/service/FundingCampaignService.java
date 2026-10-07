package com.gyf.service;

import java.util.List;

import com.gyf.dto.FundingCampaignRequest;
import com.gyf.entity.FundingCampaign;

public interface FundingCampaignService {

    FundingCampaign createCampaign(
            FundingCampaignRequest request);

    List<FundingCampaign> getAllCampaigns();

    FundingCampaign getCampaignById(Long id);

    FundingCampaign approveCampaign(Long id);

    FundingCampaign rejectCampaign(Long id);
}