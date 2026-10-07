package com.gyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.FundingCampaignRequest;
import com.gyf.entity.FundingCampaign;
import com.gyf.entity.Investor;
import com.gyf.entity.Startup;
import com.gyf.repository.FundingCampaignRepository;
import com.gyf.repository.InvestorRepository;
import com.gyf.repository.StartupRepository;

@Service
public class FundingCampaignServiceImpl
        implements FundingCampaignService {

    @Autowired
    private FundingCampaignRepository repository;

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public FundingCampaign createCampaign(
            FundingCampaignRequest request) {

        FundingCampaign campaign =
                new FundingCampaign();

        campaign.setStartupId(
                request.getStartupId());

        campaign.setCampaignTitle(
                request.getCampaignTitle());

        campaign.setTargetAmount(
                request.getTargetAmount());

        campaign.setRaisedAmount(0.0);

        Startup startup =
                startupRepository
                        .findById(request.getStartupId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Startup Not Found"));

        String createdBy =
                startup.getCreatedBy();

        if (createdBy == null ||
                createdBy.trim().isEmpty()) {

            createdBy =
                    startup.getFounderName();
        }

        campaign.setCreatedBy(createdBy);

        /*
         * Campaigns now require admin approval.
         */
        campaign.setStatus("PENDING");

        FundingCampaign saved =
                repository.save(campaign);

        notificationService.notifyAdmins(
                "Campaign Created",
                "A new funding campaign \""
                        + saved.getCampaignTitle()
                        + "\" requires review.",
                "CAMPAIGN_CREATED",
                saved.getId(),
                "/admin/campaigns");

        return saved;
    }

    @Override
    public FundingCampaign getCampaignById(
            Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Campaign Not Found"));
    }

    @Override
    public List<FundingCampaign> getAllCampaigns() {

        return repository.findAll();
    }

    @Override
    public FundingCampaign approveCampaign(
            Long id) {

        FundingCampaign campaign =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Campaign Not Found"));

        campaign.setStatus("APPROVED");

        FundingCampaign saved =
                repository.save(campaign);

        notificationService.notifyUserByIdentity(
                saved.getCreatedBy(),
                "Campaign Update",
                "Your campaign \""
                        + saved.getCampaignTitle()
                        + "\" has been approved.",
                "CAMPAIGN_APPROVED",
                saved.getId(),
                "/campaigns/"
                        + saved.getId());

        List<Investor> investors =
                investorRepository
                        .findByStatusIgnoreCase("APPROVED");

        for (Investor investor : investors) {

            notificationService.notifyUserByIdentity(
                    investor.getEmail(),
                    "New Campaign Available",
                    "A new funding campaign is now available: "
                            + saved.getCampaignTitle(),
                    "NEW_CAMPAIGN",
                    saved.getId(),
                    "/campaigns/"
                            + saved.getId());
        }

        return saved;
    }

    @Override
    public FundingCampaign rejectCampaign(
            Long id) {

        FundingCampaign campaign =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Campaign Not Found"));

        campaign.setStatus("REJECTED");

        FundingCampaign saved =
                repository.save(campaign);

        notificationService.notifyUserByIdentity(
                saved.getCreatedBy(),
                "Campaign Update",
                "Your campaign \""
                        + saved.getCampaignTitle()
                        + "\" was rejected.",
                "CAMPAIGN_REJECTED",
                saved.getId(),
                "/campaigns/"
                        + saved.getId());

        return saved;
    }
}