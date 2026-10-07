package com.gyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gyf.dto.InvestorRequest;
import com.gyf.entity.Investor;
import com.gyf.service.InvestorService;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @PostMapping
    public Investor createInvestor(@RequestBody InvestorRequest request) {

        return investorService.createInvestor(request);
    }

    @GetMapping
    public List<Investor> getAllInvestors() {

        return investorService.getAllInvestors();
    }
    
    @PutMapping("/{id}/approve")
    public Investor approveInvestor(
            @PathVariable Long id) {

        return investorService.approveInvestor(id);
    }
    
    @PutMapping("/{id}/reject")
    public Investor rejectInvestor(
            @PathVariable Long id) {

        return investorService.rejectInvestor(id);
    }
    @GetMapping("/{id}")
    public Investor getInvestorById(
            @PathVariable Long id) {

        return investorService.getInvestorById(id);
    }
}