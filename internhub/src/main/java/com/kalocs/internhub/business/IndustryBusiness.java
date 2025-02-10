package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Industry;

import java.util.List;
import java.util.Optional;

public interface IndustryBusiness {
    Optional<Industry> getById(int industryId);
    Industry getIndustryById(Integer id);
    List<Industry> getAllIndustries();
}

