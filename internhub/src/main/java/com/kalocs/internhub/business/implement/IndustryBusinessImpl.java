package com.kalocs.internhub.business.implement;


import com.kalocs.internhub.business.IndustryBusiness;
import com.kalocs.internhub.entity.Industry;
import com.kalocs.internhub.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IndustryBusinessImpl implements IndustryBusiness {

    private final IndustryRepository industryRepository;

    @Autowired
    public IndustryBusinessImpl(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public Optional<Industry> getById(int industryId) {
        return industryRepository.findById(industryId);
    }
}
