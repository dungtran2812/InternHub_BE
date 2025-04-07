package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.payload.response.DashboardResponse;
import com.kalocs.internhub.payload.response.RevenueDashboard;
import com.kalocs.internhub.payload.response.TransactionSummary;
import com.kalocs.internhub.service.DashboardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLConstant.ADMIN)
@Log4j2
@CrossOrigin("*")
public class AdminController {

    private final DashboardService dashboardService;

    @Autowired
    public AdminController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("dashboard")
    public ResponseEntity<DashboardResponse> dashboard(long startDate, long endDate) {
        log.info("dashboard() AdminController start");
        DashboardResponse dashboardData = dashboardService.dashboard(startDate, endDate);
        log.info("dashboard() AdminController end");
        return ResponseEntity.ok(dashboardData);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("revenue")
    public ResponseEntity<List<TransactionSummary>> revenue() {
        log.info("revenue() AdminController start");
        List<TransactionSummary> dashboardData = dashboardService.revenue();
        log.info("revenue() AdminController end");
        return ResponseEntity.ok(dashboardData);
    }

}
