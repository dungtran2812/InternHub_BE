package com.kalocs.internhub.service;

import com.kalocs.internhub.entity.Company;

import java.util.UUID;

public interface CompanyService {
    Company getCompany(UUID id);
}
