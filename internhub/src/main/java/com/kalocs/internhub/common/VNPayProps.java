package com.kalocs.internhub.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "vnpay")
@Component
@Data
public class VNPayProps {
    private String vnpTmnCode;
    private String vnpHashSecret;
}
