package com.gyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.InterestRequest;
import com.gyf.entity.InvestmentInterest;
import com.gyf.entity.Investor;
import com.gyf.entity.Startup;
import com.gyf.repository.InvestmentInterestRepository;
import com.gyf.repository.InvestorRepository;
import com.gyf.repository.StartupRepository;

@Service
public class InterestServiceImpl
        implements InterestService {

    @Autowired
    private InvestmentInterestRepository repository;

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public InvestmentInterest createInterest(
            InterestRequest request) {

        InvestmentInterest interest =
                new InvestmentInterest();

        interest.setStartupId(
                request.getStartupId());

        interest.setInvestorId(
                request.getInvestorId());

        interest.setStatus("PENDING");

        InvestmentInterest saved =
                repository.save(interest);

        Startup startup =
                startupRepository
                        .findById(request.getStartupId())
                        .orElse(null);

        Investor investor =
                investorRepository
                        .findById(request.getInvestorId())
                        .orElse(null);

        notificationService.notifyAdmins(
                "Investment Interest Submitted",
                "A new investor interest has been submitted.",
                "INTEREST_SUBMITTED",
                saved.getId(),
                "/admin/interests");

        if (startup != null) {

            String owner =
                    startup.getCreatedBy();

            if (owner == null ||
                    owner.trim().isEmpty()) {

                owner =
                        startup.getFounderName();
            }

            notificationService.notifyUserByIdentity(
                    owner,
                    "New Investor Interest",
                    "An investor has shown interest in your startup \""
                            + startup.getStartupName()
                            + "\".",
                    "NEW_INVESTOR_INTEREST",
                    saved.getId(),
                    "/interests");
        }

        if (investor != null) {

            notificationService.notifyUserByIdentity(
                    investor.getEmail(),
                    "Startup Matched",
                    startup != null
                            ? "Your interest has been submitted for "
                                    + startup.getStartupName()
                            : "Your startup interest has been submitted.",
                    "STARTUP_MATCHED",
                    saved.getId(),
                    "/interests");
        }

        return saved;
    }

    @Override
    public List<InvestmentInterest> getAllInterests() {

        return repository.findAll();
    }

    @Override
    public InvestmentInterest acceptInterest(
            Long id) {

        InvestmentInterest interest =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Interest Not Found"));

        interest.setStatus("ACCEPTED");

        InvestmentInterest saved =
                repository.save(interest);

        Investor investor =
                investorRepository
                        .findById(saved.getInvestorId())
                        .orElse(null);

        if (investor != null) {

            notificationService.notifyUserByIdentity(
                    investor.getEmail(),
                    "Interest Accepted",
                    "Your investment interest has been accepted.",
                    "INTEREST_ACCEPTED",
                    saved.getId(),
                    "/interests");
        }

        return saved;
    }

    @Override
    public InvestmentInterest rejectInterest(
            Long id) {

        InvestmentInterest interest =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Interest Not Found"));

        interest.setStatus("REJECTED");

        return repository.save(interest);
    }
}