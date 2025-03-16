package com.kalocs.internhub.model;

import com.kalocs.internhub.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PremiumPlanDTO {
    private String id;
    private String name;
    private UserRole role;
    private double price;
    private long duration;
}
