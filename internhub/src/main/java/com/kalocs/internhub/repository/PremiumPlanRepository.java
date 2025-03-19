package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.PremiumPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PremiumPlanRepository extends JpaRepository<PremiumPlan, UUID> {
    PremiumPlan findPremiumPlanById(UUID id);
}
