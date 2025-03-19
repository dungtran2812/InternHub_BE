package com.kalocs.internhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_subscriptions")
public class UserSubscription {
    @Id
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "plan_id", nullable = false)
    private PremiumPlan plan;

    @Column(nullable = false)
    private long startDate;

    @Column(nullable = false)
    private long expiryDate;
}
