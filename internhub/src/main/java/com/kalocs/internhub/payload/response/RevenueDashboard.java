package com.kalocs.internhub.payload.response;

import com.kalocs.internhub.model.PremiumPlanDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueDashboard {
    private PremiumPlanDTO premiumPlan;
    private int quantity;
}
