package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Company;

import java.util.UUID;

public interface CompanyBusiness {
    Company getCompany(UUID id);
}
