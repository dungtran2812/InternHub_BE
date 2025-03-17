package com.kalocs.internhub.service;

import vn.payos.type.Webhook;
import vn.payos.type.WebhookData;

import java.util.UUID;

public interface PremiumService {
    String byPremium(UUID id);

    boolean redirectPayOS(long orderCode);
}
