package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.model.CompanyDTO;
import com.kalocs.internhub.payload.request.CompanyRequest;
import com.kalocs.internhub.service.CompanyService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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
        log.debug("getAllCompanies CompanyServiceImpl start");
        List<CompanyDTO> companies = companyBusiness.getAllCompanies().stream()
                .map( company -> modelMapper.map(company, CompanyDTO.class)).toList();
        log.debug("getAllCompanies CompanyServiceImpl end");
        return companies;
    }

    @Override
    public CompanyDTO createCompany(CompanyRequest company) {
        try{
            log.debug("createCompany CompanyServiceImpl start | {}", company);
            Company companyToCreate = modelMapper.map(company, Company.class);
            companyToCreate.setId(UUID.randomUUID());
            CompanyDTO createdCompany = modelMapper.map(companyBusiness.createCompany(companyToCreate), CompanyDTO.class);
            log.debug("createCompany CompanyServiceImpl end | {}", createdCompany);
            return createdCompany;
        } catch (Exception e) {
            log.error("createCompany CompanyServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public CompanyDTO updateCompany(UUID id, CompanyRequest company) {
        try{
            log.debug("updateCompany CompanyServiceImpl start | {}", company);
            Company companyToUpdate = modelMapper.map(company, Company.class);
            companyToUpdate.setId(id);
            CompanyDTO updatedCompany = modelMapper.map(companyBusiness.updateCompany(companyToUpdate), CompanyDTO.class);
            log.debug("updateCompany CompanyServiceImpl end | {}", updatedCompany);
            return updatedCompany;
        } catch (Exception e) {
            log.error("updateCompany CompanyServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteCompany(UUID id) {
        try{
            log.debug("deleteCompany CompanyServiceImpl start | {}", id);
            boolean check = companyBusiness.deleteCompany(id);
            log.debug("deleteCompany CompanyServiceImpl end | {}", id);
            return check;
        } catch (Exception e) {
            log.error("deleteCompany CompanyServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
