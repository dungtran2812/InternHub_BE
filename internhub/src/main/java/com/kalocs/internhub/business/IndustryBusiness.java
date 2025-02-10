package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Industry;

import java.util.List;

public interface IndustryBusiness {
    Industry getIndustryById(Integer id);
    List<Industry> getAllIndustries();
}
