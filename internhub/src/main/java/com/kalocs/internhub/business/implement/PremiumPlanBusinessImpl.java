package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.PremiumPlanBusiness;
import com.kalocs.internhub.entity.PremiumPlan;
import com.kalocs.internhub.repository.PremiumPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumPlanBusinessImpl extends BaseBusinessImpl<PremiumPlan, PremiumPlanRepository> implements PremiumPlanBusiness {

    @Autowired
    public PremiumPlanBusinessImpl(PremiumPlanRepository premiumPlanRepository) {
        super(premiumPlanRepository);
    }
}
