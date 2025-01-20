package com.kalocs.internhub.service;

import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.model.CompanyDTO;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    Company getCompany(UUID id);
    List<CompanyDTO> getAllCompanies();
}
