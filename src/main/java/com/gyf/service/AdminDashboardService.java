package com.gyf.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.AdminDashboardResponse;
import com.gyf.dto.PlatformActivityResponse;
import com.gyf.entity.FundingCampaign;
import com.gyf.entity.InvestmentInterest;
import com.gyf.entity.Investor;
import com.gyf.entity.Startup;
import com.gyf.entity.User;
import com.gyf.repository.FundingCampaignRepository;
import com.gyf.repository.InvestmentInterestRepository;
import com.gyf.repository.InvestorRepository;
import com.gyf.repository.StartupRepository;
import com.gyf.repository.UserRepository;

@Service
public class AdminDashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private FundingCampaignRepository campaignRepository;

    @Autowired
    private InvestmentInterestRepository interestRepository;

    public AdminDashboardResponse getDashboard() {

        AdminDashboardResponse response =
                new AdminDashboardResponse();

        response.setTotalUsers(
                userRepository.count());

        response.setTotalStartups(
                startupRepository.count());

        response.setTotalInvestors(
                investorRepository.count());

        response.setTotalCampaigns(
                campaignRepository.count());

        response.setPendingStartups(
                startupRepository
                        .countByStatus("PENDING"));

        response.setPendingInvestors(
                investorRepository
                        .countByStatus("PENDING"));

        response.setPendingCampaigns(
                campaignRepository
                        .countByStatus("PENDING"));

        response.setRecentActivity(
                getRecentActivity());

        return response;
    }

    private List<PlatformActivityResponse>
            getRecentActivity() {

        List<PlatformActivityResponse> activities =
                new ArrayList<>();

        List<User> users =
                userRepository
                        .findTop10ByOrderByCreatedDateDesc();

        for (User user : users) {

            activities.add(
                    new PlatformActivityResponse(
                            user.getId(),
                            "USER",
                            "New User Registered",
                            user.getName()
                                    + " registered on GYF.",
                            user.getCreatedDate()));
        }

        List<Startup> startups =
                startupRepository
                        .findTop10ByOrderByCreatedDateDesc();

        for (Startup startup : startups) {

            activities.add(
                    new PlatformActivityResponse(
                            startup.getId(),
                            "STARTUP",
                            "Startup Submitted",
                            startup.getStartupName()
                                    + " was submitted.",
                            startup.getCreatedDate()));
        }

        List<Investor> investors =
                investorRepository
                        .findTop10ByOrderByCreatedDateDesc();

        for (Investor investor : investors) {

            activities.add(
                    new PlatformActivityResponse(
                            investor.getId(),
                            "INVESTOR",
                            "Investor Registered",
                            investor.getInvestorName()
                                    + " registered.",
                            investor.getCreatedDate()));
        }

        List<FundingCampaign> campaigns =
                campaignRepository
                        .findTop10ByOrderByCreatedDateDesc();

        for (FundingCampaign campaign :
                campaigns) {

            activities.add(
                    new PlatformActivityResponse(
                            campaign.getId(),
                            "CAMPAIGN",
                            "Campaign Created",
                            campaign.getCampaignTitle()
                                    + " was created.",
                            campaign.getCreatedDate()));
        }

        List<InvestmentInterest> interests =
                interestRepository
                        .findTop10ByOrderByCreatedDateDesc();

        for (InvestmentInterest interest :
                interests) {

            activities.add(
                    new PlatformActivityResponse(
                            interest.getId(),
                            "INTEREST",
                            "Investment Interest",
                            "A new investment interest was submitted.",
                            interest.getCreatedDate()));
        }

        activities.sort(
                Comparator.comparing(
                        PlatformActivityResponse
                                ::getCreatedDate,
                        Comparator.nullsLast(
                                Comparator.reverseOrder())));

        if (activities.size() > 10) {

            return new ArrayList<>(
                    activities.subList(0, 10));
        }

        return activities;
    }
}