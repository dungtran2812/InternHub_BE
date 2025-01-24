package com.kalocs.internhub.service;

import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.model.CompanyDTO;
import com.kalocs.internhub.payload.request.CompanyRequest;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    Company getCompany(UUID id);
    List<CompanyDTO> getAllCompanies();

    CompanyDTO createCompany(CompanyRequest company);

    CompanyDTO updateCompany(UUID id, CompanyRequest company);

    boolean deleteCompany(UUID id);
}
