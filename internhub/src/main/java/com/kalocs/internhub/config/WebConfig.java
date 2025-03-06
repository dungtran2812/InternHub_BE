package com.kalocs.internhub.config;

import com.kalocs.internhub.common.PayOSProps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.payos.PayOS;

@Configuration
public class WebConfig {

    private final PayOSProps payOSProps;

    public WebConfig(PayOSProps payOSProps) {
        this.payOSProps = payOSProps;
    }

    @Bean
    public PayOS payOS() {
        return new PayOS(payOSProps.getClientId(), payOSProps.getApiKey(), payOSProps.getChecksumKey());
    }
}
