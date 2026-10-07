package com.gyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gyf.dto.InterestRequest;
import com.gyf.entity.InvestmentInterest;
import com.gyf.service.InterestService;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;

    @PostMapping
    public InvestmentInterest createInterest(
            @RequestBody InterestRequest request) {

        return interestService
                .createInterest(request);
    }

    @GetMapping
    public List<InvestmentInterest>
            getAllInterests() {

        return interestService
                .getAllInterests();
    }

    @PutMapping("/{id}/accept")
    public InvestmentInterest accept(
            @PathVariable Long id) {

        return interestService
                .acceptInterest(id);
    }

    @PutMapping("/{id}/reject")
    public InvestmentInterest reject(
            @PathVariable Long id) {

        return interestService
                .rejectInterest(id);
    }
    
    
}