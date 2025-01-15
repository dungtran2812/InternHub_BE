package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.repository.CompanyRepository;
import com.kalocs.internhub.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompanyServiceImpl implements CompanyService {

    private final CompanyBusiness companyBusiness;

    @Autowired
    public CompanyServiceImpl(CompanyBusiness companyBusiness) {
        this.companyBusiness = companyBusiness;
    }

    @Override
    public Company getCompany(UUID id) {
        return companyBusiness.getCompany(id);
    }
}
