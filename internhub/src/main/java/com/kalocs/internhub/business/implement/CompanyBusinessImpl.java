package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompanyBusinessImpl implements CompanyBusiness {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyBusinessImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company getCompany(UUID id) {
        return companyRepository.findCompanyById(id);
    }
}
