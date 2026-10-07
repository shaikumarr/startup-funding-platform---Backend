package com.gyf.service;

import java.util.List;

import com.gyf.dto.InvestorRequest;
import com.gyf.entity.Investor;

public interface InvestorService {

    Investor createInvestor(InvestorRequest request);

    List<Investor> getAllInvestors();
    
    Investor approveInvestor(Long id);

    Investor rejectInvestor(Long id);
    Investor getInvestorById(Long id);

}