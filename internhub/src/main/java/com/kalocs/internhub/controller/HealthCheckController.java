package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstant.HEALTH_CHECK)
@Log4j2
public class HealthCheckController {
    @GetMapping
    public String healthCheck() {
        log.info("Health Check Started");
        return "OK";
    }

}
