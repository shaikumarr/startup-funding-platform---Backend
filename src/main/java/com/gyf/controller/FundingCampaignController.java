package com.gyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gyf.dto.FundingCampaignRequest;
import com.gyf.entity.FundingCampaign;
import com.gyf.service.FundingCampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class FundingCampaignController {

    @Autowired
    private FundingCampaignService fundingCampaignService;

    @PostMapping
    public FundingCampaign createCampaign(
            @RequestBody FundingCampaignRequest request) {

        return fundingCampaignService
                .createCampaign(request);
    }

    @GetMapping
    public List<FundingCampaign> getAllCampaigns() {

        return fundingCampaignService
                .getAllCampaigns();
    }

    @GetMapping("/{id}")
    public FundingCampaign getCampaignById(
            @PathVariable Long id) {

        return fundingCampaignService
                .getCampaignById(id);
    }

    @PutMapping("/{id}/approve")
    public FundingCampaign approveCampaign(
            @PathVariable Long id) {

        return fundingCampaignService
                .approveCampaign(id);
    }

    @PutMapping("/{id}/reject")
    public FundingCampaign rejectCampaign(
            @PathVariable Long id) {

        return fundingCampaignService
                .rejectCampaign(id);
    }
}