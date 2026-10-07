package com.gyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyf.dto.StartupRequest;
import com.gyf.entity.Startup;
import com.gyf.service.StartupService;

@RestController
@RequestMapping("/api/startups")
public class StartupController {

    @Autowired
    private StartupService startupService;

    @PostMapping
    public Startup createStartup(
            @RequestBody StartupRequest request) {

        return startupService.createStartup(request);
    }

    @GetMapping
    public List<Startup> getAllStartups() {

        return startupService.getAllStartups();
    }

    @GetMapping("/{id:[0-9]+}")
    public Startup getStartupById(
            @PathVariable Long id) {

        return startupService.getStartupById(id);
    }

    /*
     * EDIT STARTUP
     */
    @PutMapping("/{id:[0-9]+}")
    public Startup updateStartup(
            @PathVariable Long id,
            @RequestBody StartupRequest request) {

        return startupService.updateStartup(
                id,
                request);
    }

    /*
     * APPROVE STARTUP
     */
    @PutMapping("/{id:[0-9]+}/approve")
    public Startup approveStartup(
            @PathVariable Long id) {

        return startupService.approveStartup(id);
    }

    /*
     * REJECT STARTUP
     */
    @PutMapping("/{id:[0-9]+}/reject")
    public Startup rejectStartup(
            @PathVariable Long id) {

        return startupService.rejectStartup(id);
    }

    /*
     * DELETE STARTUP
     */
    @DeleteMapping("/{id:[0-9]+}")
    public String deleteStartup(
            @PathVariable Long id) {

        startupService.deleteStartup(id);

        return "Startup Deleted Successfully";
    }
}