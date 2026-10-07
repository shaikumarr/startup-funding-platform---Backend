package com.gyf.service;

import java.util.List;

import com.gyf.dto.InterestRequest;
import com.gyf.entity.InvestmentInterest;

public interface InterestService {

    InvestmentInterest createInterest(
            InterestRequest request);

    List<InvestmentInterest> getAllInterests();

    InvestmentInterest acceptInterest(Long id);

    InvestmentInterest rejectInterest(Long id);
}