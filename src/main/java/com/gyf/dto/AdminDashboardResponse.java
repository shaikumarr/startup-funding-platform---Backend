package com.gyf.dto;

import java.util.List;

public class AdminDashboardResponse {

    private long totalUsers;

    private long totalStartups;

    private long totalInvestors;

    private long totalCampaigns;

    private long pendingStartups;

    private long pendingInvestors;

    private long pendingCampaigns;

    private List<PlatformActivityResponse> recentActivity;

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalStartups() {
        return totalStartups;
    }

    public void setTotalStartups(long totalStartups) {
        this.totalStartups = totalStartups;
    }

    public long getTotalInvestors() {
        return totalInvestors;
    }

    public void setTotalInvestors(long totalInvestors) {
        this.totalInvestors = totalInvestors;
    }

    public long getTotalCampaigns() {
        return totalCampaigns;
    }

    public void setTotalCampaigns(long totalCampaigns) {
        this.totalCampaigns = totalCampaigns;
    }

    public long getPendingStartups() {
        return pendingStartups;
    }

    public void setPendingStartups(
            long pendingStartups) {

        this.pendingStartups = pendingStartups;
    }

    public long getPendingInvestors() {
        return pendingInvestors;
    }

    public void setPendingInvestors(
            long pendingInvestors) {

        this.pendingInvestors = pendingInvestors;
    }

    public long getPendingCampaigns() {
        return pendingCampaigns;
    }

    public void setPendingCampaigns(
            long pendingCampaigns) {

        this.pendingCampaigns = pendingCampaigns;
    }

    public List<PlatformActivityResponse>
            getRecentActivity() {

        return recentActivity;
    }

    public void setRecentActivity(
            List<PlatformActivityResponse> recentActivity) {

        this.recentActivity = recentActivity;
    }
}