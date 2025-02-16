package com.kalocs.internhub.service;

import com.kalocs.internhub.model.IndustryDTO;

import java.util.List;

public interface IndustryService {
    IndustryDTO getIndustryById(Integer id);
    List<IndustryDTO> getAllIndustries();
}
