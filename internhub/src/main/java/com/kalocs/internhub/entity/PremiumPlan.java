package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "premium_plans")
public class PremiumPlan {
    @Id
    private UUID id;
    private String name;
    private UserRole role;
    private double price;
    private long duration;
}
