package com.kalocs.internhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubscriptionDTO {
    private String id;
    private PremiumPlanDTO plan;
    private long startDate;
    private long expiryDate;
}
