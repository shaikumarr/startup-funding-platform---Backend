package com.gyf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "GYF Backend Running Successfully";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
