package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyBusiness extends BaseBusiness<Company> {
    Company getCompany(UUID id);
    List<Company> getAllCompanies();
    Company createCompany(Company company);

    Company updateCompany(Company companyToUpdate);

    boolean deleteCompany(UUID id);
}
