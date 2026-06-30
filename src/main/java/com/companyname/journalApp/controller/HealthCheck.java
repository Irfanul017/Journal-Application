package com.companyname.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health-check") // end point URL + verb = REST API
    public String healthCheck(){
        return "OK";
    }
}
