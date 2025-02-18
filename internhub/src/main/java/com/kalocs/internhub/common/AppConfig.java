package com.kalocs.internhub.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

@Data
@EnableAsync
@Configuration
public class AppConfig {
    @Value("${internhub.app.authorizedRedirectUris}")
    private List<String> authorizedRedirectUris = new ArrayList<>();
    @Value("${internhub.app.jwtSecret}")
    private String tokenSecret;
    @Value("${internhub.app.jwtExpirationMs}")
    private long tokenExpirationMsec;
}