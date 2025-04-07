package com.kalocs.internhub.model;

import com.kalocs.internhub.common.UserRole;
import com.kalocs.internhub.entity.PremiumPlan;
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

    public PremiumPlanDTO(PremiumPlan plan){
        this.id = plan.getId().toString();
        this.name = plan.getName();
        this.role = plan.getRole();
        this.price = plan.getPrice();
        this.duration = plan.getDuration();
    }
}
