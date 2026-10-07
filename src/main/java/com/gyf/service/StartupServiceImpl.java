package com.gyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyf.dto.StartupRequest;
import com.gyf.entity.Investor;
import com.gyf.entity.Startup;
import com.gyf.repository.InvestorRepository;
import com.gyf.repository.StartupRepository;

@Service
public class StartupServiceImpl
        implements StartupService {

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Startup createStartup(
            StartupRequest request) {

        if (request == null) {
            throw new RuntimeException(
                    "Startup request cannot be empty");
        }

        Startup startup =
                new Startup();

        startup.setStartupName(
                request.getStartupName());

        startup.setFounderName(
                request.getFounderName());

        startup.setIndustry(
                request.getIndustry());

        startup.setDescription(
                request.getDescription());

        startup.setWebsite(
                request.getWebsite());

        startup.setLocation(
                request.getLocation());

        startup.setValuation(
                request.getValuation());

        startup.setFundingRequired(
                request.getFundingRequired());

        String createdBy =
                request.getCreatedBy();

        if (createdBy == null ||
                createdBy.trim().isEmpty()) {

            createdBy =
                    request.getFounderName();
        }

        startup.setCreatedBy(createdBy);

        startup.setStatus("PENDING");

        /*
         * First save the startup.
         */
        Startup saved =
                startupRepository.save(startup);

        /*
         * Notification should never prevent
         * successful startup creation.
         */
        try {

            notificationService.notifyAdmins(
                    "Startup Approval Required",
                    saved.getStartupName()
                            + " has been submitted for approval.",
                    "STARTUP_APPROVAL",
                    saved.getId(),
                    "/admin/startups");

        } catch (Exception notificationError) {

            System.err.println(
                    "Startup notification failed: "
                            + notificationError.getMessage());

            notificationError.printStackTrace();
        }

        return saved;
    }
    
    @Override
    public Startup updateStartup(
            Long id,
            StartupRequest request) {

        if (id == null) {
            throw new RuntimeException(
                    "Startup ID is required");
        }

        if (request == null) {
            throw new RuntimeException(
                    "Startup request cannot be empty");
        }

        Startup startup =
                startupRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Startup Not Found"));

        if (request.getStartupName() == null ||
                request.getStartupName()
                        .trim()
                        .isEmpty()) {

            throw new RuntimeException(
                    "Startup name is required");
        }

        if (request.getFounderName() == null ||
                request.getFounderName()
                        .trim()
                        .isEmpty()) {

            throw new RuntimeException(
                    "Founder name is required");
        }

        if (request.getIndustry() == null ||
                request.getIndustry()
                        .trim()
                        .isEmpty()) {

            throw new RuntimeException(
                    "Industry is required");
        }

        if (request.getDescription() == null ||
                request.getDescription()
                        .trim()
                        .isEmpty()) {

            throw new RuntimeException(
                    "Description is required");
        }

        if (request.getLocation() == null ||
                request.getLocation()
                        .trim()
                        .isEmpty()) {

            throw new RuntimeException(
                    "Location is required");
        }

        startup.setStartupName(
                request.getStartupName().trim());

        startup.setFounderName(
                request.getFounderName().trim());

        startup.setIndustry(
                request.getIndustry().trim());

        startup.setDescription(
                request.getDescription().trim());

        startup.setWebsite(
                request.getWebsite());

        startup.setLocation(
                request.getLocation().trim());

        startup.setValuation(
                request.getValuation());

        startup.setFundingRequired(
                request.getFundingRequired());

        /*
         * Do not change createdBy or status
         * during normal editing.
         */

        return startupRepository.save(
                startup);
    }


    @Override
    public void deleteStartup(
            Long id) {

        if (id == null) {
            throw new RuntimeException(
                    "Startup ID is required");
        }

        Startup startup =
                startupRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Startup Not Found"));

        startupRepository.delete(startup);
    }

    @Override
    public List<Startup> getAllStartups() {

        return startupRepository.findAll();
    }

    @Override
    public Startup approveStartup(Long id) {

        Startup startup =
                startupRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Startup Not Found"));

        startup.setStatus("APPROVED");

        Startup saved =
                startupRepository.save(startup);

        String owner =
                saved.getCreatedBy();

        if (owner == null ||
                owner.trim().isEmpty()) {

            owner =
                    saved.getFounderName();
        }

        try {

            notificationService.notifyUserByIdentity(
                    owner,
                    "Startup Approved",
                    "Your startup \""
                            + saved.getStartupName()
                            + "\" has been approved.",
                    "STARTUP_APPROVED",
                    saved.getId(),
                    "/startups");

        } catch (Exception notificationError) {

            System.err.println(
                    "Approval notification failed: "
                            + notificationError.getMessage());
        }

        notifyMatchingInvestors(saved);

        return saved;
    }

    @Override
    public Startup rejectStartup(Long id) {

        Startup startup =
                startupRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Startup Not Found"));

        startup.setStatus("REJECTED");

        Startup saved =
                startupRepository.save(startup);

        String owner =
                saved.getCreatedBy();

        if (owner == null ||
                owner.trim().isEmpty()) {

            owner =
                    saved.getFounderName();
        }

        try {

            notificationService.notifyUserByIdentity(
                    owner,
                    "Startup Rejected",
                    "Your startup \""
                            + saved.getStartupName()
                            + "\" was rejected.",
                    "STARTUP_REJECTED",
                    saved.getId(),
                    "/startups");

        } catch (Exception notificationError) {

            System.err.println(
                    "Rejection notification failed: "
                            + notificationError.getMessage());
        }

        return saved;
    }

    private void notifyMatchingInvestors(
            Startup startup) {

        List<Investor> investors =
                investorRepository
                        .findByStatusIgnoreCase(
                                "APPROVED");

        for (Investor investor :
                investors) {

            String preferred =
                    investor.getPreferredIndustry();

            if (preferred == null ||
                    preferred.trim().isEmpty() ||
                    preferred.equalsIgnoreCase("ALL") ||
                    preferred.equalsIgnoreCase(
                            startup.getIndustry())) {

                try {

                    notificationService
                            .notifyUserByIdentity(
                                    investor.getEmail(),
                                    "Startup Matched",
                                    "A startup matching your investment preferences is now available: "
                                            + startup.getStartupName(),
                                    "STARTUP_MATCHED",
                                    startup.getId(),
                                    "/startups/"
                                            + startup.getId());

                } catch (
                        Exception notificationError) {

                    System.err.println(
                            "Investor notification failed: "
                                    + notificationError
                                            .getMessage());
                }
            }
        }
    }

    @Override
    public Startup getStartupById(
            Long id) {

        return startupRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Startup Not Found"));
    }
}