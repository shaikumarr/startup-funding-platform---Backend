package com.gyf.dto;

public class DashboardResponse {

    private long totalUsers;

    private long totalStartups;

    private long totalInvestors;

    private long totalCampaigns;

    private long approvedStartups;

    private long pendingStartups;

    private long approvedInvestors;

    private long pendingInvestors;

    private long pendingCampaigns;

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

    public long getApprovedStartups() {
        return approvedStartups;
    }

    public void setApprovedStartups(long approvedStartups) {
        this.approvedStartups = approvedStartups;
    }

    public long getPendingStartups() {
        return pendingStartups;
    }

    public void setPendingStartups(long pendingStartups) {
        this.pendingStartups = pendingStartups;
    }

    public long getApprovedInvestors() {
        return approvedInvestors;
    }

    public void setApprovedInvestors(long approvedInvestors) {
        this.approvedInvestors = approvedInvestors;
    }

    public long getPendingInvestors() {
        return pendingInvestors;
    }

    public void setPendingInvestors(long pendingInvestors) {
        this.pendingInvestors = pendingInvestors;
    }

    public long getPendingCampaigns() {
        return pendingCampaigns;
    }

    public void setPendingCampaigns(
            long pendingCampaigns) {

        this.pendingCampaigns = pendingCampaigns;
    }
}