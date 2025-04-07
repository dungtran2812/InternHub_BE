package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.UserSubscriptionBusiness;
import com.kalocs.internhub.entity.PremiumPlan;
import com.kalocs.internhub.entity.UserSubscription;
import com.kalocs.internhub.model.PremiumPlanDTO;
import com.kalocs.internhub.payload.response.RevenueDashboard;
import com.kalocs.internhub.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSubscriptionBusinessImpl extends BaseBusinessImpl<UserSubscription, UserSubscriptionRepository> implements UserSubscriptionBusiness {

    @Autowired
    public UserSubscriptionBusinessImpl(UserSubscriptionRepository userSubscriptionRepository) {
        super(userSubscriptionRepository);
    }

    @Override
    public List<RevenueDashboard> getRevenue() {
        List<Object[]> rawData = repository.getRevenueDashboardRaw();

        return rawData.stream().map(row -> {
            PremiumPlan plan = (PremiumPlan) row[0];
            Long count = (Long) row[1];
            PremiumPlanDTO dto = new PremiumPlanDTO(plan);
            return new RevenueDashboard(dto, count.intValue());
        }).collect(Collectors.toList());
    }
}
