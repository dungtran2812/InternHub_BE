package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.UserSubscription;
import com.kalocs.internhub.payload.response.RevenueDashboard;

import java.util.List;

public interface UserSubscriptionBusiness extends BaseBusiness<UserSubscription>{
    List<RevenueDashboard> getRevenue();
}
