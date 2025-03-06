package com.kalocs.internhub.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "payos")
public class PayOSProps {
    private String clientId;
    private String apiKey;
    private String checksumKey;
}
