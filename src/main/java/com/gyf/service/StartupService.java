package com.gyf.service;

import java.util.List;

import com.gyf.dto.StartupRequest;
import com.gyf.entity.Startup;

public interface StartupService {

    Startup createStartup(
            StartupRequest request);

    List<Startup> getAllStartups();

    Startup getStartupById(
            Long id);

    Startup updateStartup(
            Long id,
            StartupRequest request);

    Startup approveStartup(
            Long id);

    Startup rejectStartup(
            Long id);

    void deleteStartup(
            Long id);
}