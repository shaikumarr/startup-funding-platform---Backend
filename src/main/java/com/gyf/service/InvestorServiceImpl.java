package com.gyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.InvestorRequest;
import com.gyf.entity.Investor;
import com.gyf.repository.InvestorRepository;

@Service
public class InvestorServiceImpl
        implements InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Investor createInvestor(
            InvestorRequest request) {

        Investor investor = new Investor();

        investor.setInvestorName(
                request.getInvestorName());

        investor.setCompanyName(
                request.getCompanyName());

        investor.setEmail(
                request.getEmail());

        investor.setMobile(
                request.getMobile());

        investor.setInvestmentRange(
                request.getInvestmentRange());

        investor.setPreferredIndustry(
                request.getPreferredIndustry());

        investor.setCreatedBy(
                request.getEmail());

        investor.setStatus("PENDING");

        Investor saved =
                investorRepository.save(investor);

        notificationService.notifyAdmins(
                "New Investor Registered",
                saved.getInvestorName()
                        + " has registered as an investor.",
                "NEW_INVESTOR",
                saved.getId(),
                "/admin/investors");

        notificationService.notifyAdmins(
                "Investor Approval Required",
                saved.getInvestorName()
                        + " is waiting for approval.",
                "INVESTOR_APPROVAL",
                saved.getId(),
                "/admin/investors");

        return saved;
    }

    @Override
    public Investor approveInvestor(Long id) {

        Investor investor =
                investorRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Investor Not Found"));

        investor.setStatus("APPROVED");

        Investor saved =
                investorRepository.save(investor);

        notificationService.notifyUserByIdentity(
                saved.getEmail(),
                "Investor Approved",
                "Your investor account has been approved.",
                "INVESTOR_APPROVED",
                saved.getId(),
                "/dashboard");

        return saved;
    }

    @Override
    public Investor rejectInvestor(Long id) {

        Investor investor =
                investorRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Investor Not Found"));

        investor.setStatus("REJECTED");

        Investor saved =
                investorRepository.save(investor);

        notificationService.notifyUserByIdentity(
                saved.getEmail(),
                "Investor Rejected",
                "Your investor account was rejected.",
                "INVESTOR_REJECTED",
                saved.getId(),
                "/dashboard");

        return saved;
    }

    @Override
    public Investor getInvestorById(Long id) {

        return investorRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Investor Not Found"));
    }

    @Override
    public List<Investor> getAllInvestors() {

        return investorRepository.findAll();
    }
}