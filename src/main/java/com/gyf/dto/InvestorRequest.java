package com.gyf.dto;

public class InvestorRequest {

    private String investorName;

    private String companyName;

    private String email;

    private String mobile;

    private String investmentRange;

    private String preferredIndustry;

    private String createdBy;

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvestmentRange() {
        return investmentRange;
    }

    public void setInvestmentRange(String investmentRange) {
        this.investmentRange = investmentRange;
    }

    public String getPreferredIndustry() {
        return preferredIndustry;
    }

    public void setPreferredIndustry(
            String preferredIndustry) {

        this.preferredIndustry =
                preferredIndustry;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}