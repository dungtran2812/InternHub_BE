package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.model.CompanyDTO;
import com.kalocs.internhub.repository.CompanyRepository;
import com.kalocs.internhub.service.CompanyService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Log4j2
public class CompanyServiceImpl implements CompanyService {

    private final CompanyBusiness companyBusiness;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyBusiness companyBusiness) {
        this.companyBusiness = companyBusiness;
    }

    @Override
    public Company getCompany(UUID id) {
        return companyBusiness.getCompany(id);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        log.info("getAllCompanies ComapnyServiceImpl start");
        List<CompanyDTO> companies = companyBusiness.getAllCompanies().stream()
                .map( company -> modelMapper.map(company, CompanyDTO.class)).toList();
        log.info("getAllCompanies ComapnyServiceImpl end");
        return companies;
    }
}
