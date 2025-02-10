package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.IndustryBusiness;
import com.kalocs.internhub.entity.Industry;
import com.kalocs.internhub.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndustryBusinessImpl implements IndustryBusiness {
    private final IndustryRepository industryRepository;

    @Autowired
    public IndustryBusinessImpl(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public Industry getIndustryById(Integer id) {
        return industryRepository.findIndustryById(id);
    }

    @Override
    public List<Industry> getAllIndustries() {
        return industryRepository.findAll();
    }
}
