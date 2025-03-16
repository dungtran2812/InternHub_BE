package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.UserSubscriptionBusiness;
import com.kalocs.internhub.entity.UserSubscription;
import com.kalocs.internhub.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionBusinessImpl extends BaseBusinessImpl<UserSubscription, UserSubscriptionRepository> implements UserSubscriptionBusiness {

    @Autowired
    public UserSubscriptionBusinessImpl(UserSubscriptionRepository userSubscriptionRepository) {
        super(userSubscriptionRepository);
    }
}
