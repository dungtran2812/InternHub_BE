package com.kalocs.internhub.service;

import java.util.UUID;

public interface PremiumService {
    String byPremium(UUID id);

    String redirectPayOS(long orderCode);
}
