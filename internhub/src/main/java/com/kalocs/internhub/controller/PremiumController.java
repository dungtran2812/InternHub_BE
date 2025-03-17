package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.payload.response.ResponseMessage;
import com.kalocs.internhub.service.PremiumService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(URLConstant.PREMIUM)
@Log4j2
@CrossOrigin("*")
public class PremiumController {

    private final PremiumService premiumService;

    @Autowired
    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
    }

    // Student and Recruiter buy premium by premium plan id
    @PostMapping("/buy-premium")
    @PreAuthorize("hasRole('STUDENT') or hasRole('RECRUITER')")
    @Operation(summary = "Buy premium by premium plan id" , description = "Student and Recruiter buy premium by premium plan id")
    public ResponseEntity<ResponseMessage> buyPremium(UUID premiumPlanId) {
        log.info("buyPremium() PremiumController start");
        String url = premiumService.byPremium(premiumPlanId);
        log.info("buyPremium() PremiumController end");
        return ResponseEntity.ok().body(ResponseMessage.builder().success(true).message(url).build());
    }



}
