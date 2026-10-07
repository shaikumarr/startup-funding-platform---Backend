package com.gyf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyf.dto.AdminDashboardResponse;
import com.gyf.dto.DashboardResponse;
import com.gyf.repository.FundingCampaignRepository;
import com.gyf.repository.InvestorRepository;
import com.gyf.repository.StartupRepository;
import com.gyf.repository.UserRepository;
import com.gyf.service.AdminDashboardService;

@RestController
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private FundingCampaignRepository campaignRepository;

    @Autowired
    private AdminDashboardService adminDashboardService;

    /*
     * Existing dashboard endpoint.
     */
    @GetMapping("/api/dashboard")
    public DashboardResponse getDashboard() {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalUsers(
                userRepository.count());

        response.setTotalStartups(
                startupRepository.count());

        response.setTotalInvestors(
                investorRepository.count());

        response.setTotalCampaigns(
                campaignRepository.count());

        response.setApprovedStartups(
                startupRepository
                        .countByStatus("APPROVED"));

        response.setPendingStartups(
                startupRepository
                        .countByStatus("PENDING"));

        response.setApprovedInvestors(
                investorRepository
                        .countByStatus("APPROVED"));

        response.setPendingInvestors(
                investorRepository
                        .countByStatus("PENDING"));

        response.setPendingCampaigns(
                campaignRepository
                        .countByStatus("PENDING"));

        return response;
    }

    /*
     * ADMIN DASHBOARD
     */
    @GetMapping("/api/admin/dashboard")
    public AdminDashboardResponse getAdminDashboard() {

        return adminDashboardService
                .getDashboard();
    }
}