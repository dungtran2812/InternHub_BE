package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UUID> {

    @Query("SELECT us.plan, COUNT(us) FROM UserSubscription us GROUP BY us.plan")
    List<Object[]> getRevenueDashboardRaw();
}
