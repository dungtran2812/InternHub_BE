package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.config.handler.AppException;
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
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyBusiness companyBusiness, ModelMapper modelMapper) {
        this.companyBusiness = companyBusiness;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyDTO getCompany(UUID id) {
        try {
            log.debug("getCompany CompanyServiceImpl start | {}", id);
            CompanyDTO company = modelMapper.map(companyBusiness.getById(id).orElseThrow(() -> new AppException(404,"Cannot find Company with id: " + id.toString())), CompanyDTO.class);
            log.debug("getCompany CompanyServiceImpl end | {}", company);
            return company;
        } catch (Exception e) {
            log.error("getCompany CompanyServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        log.debug("getAllCompanies CompanyServiceImpl start");
        List<CompanyDTO> companies = companyBusiness.getAll().stream()
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
            CompanyDTO createdCompany = modelMapper.map(companyBusiness.create(companyToCreate), CompanyDTO.class);
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
            if (companyBusiness.getById(id).isEmpty()) {
                throw new AppException(404, "Không tìm thấy công ty với id: " + id.toString());
            }
            Company companyToUpdate = modelMapper.map(company, Company.class);
            companyToUpdate.setId(id);
            CompanyDTO updatedCompany = modelMapper.map(companyBusiness.update(companyToUpdate), CompanyDTO.class);
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
            if (companyBusiness.getById(id).isEmpty()) {
                throw new AppException(404, "Không tìm thấy công ty với id: " + id.toString());
            }
            boolean check = companyBusiness.delete(id);
            log.debug("deleteCompany CompanyServiceImpl end | {}", id);
            return check;
        } catch (Exception e) {
            log.error("deleteCompany CompanyServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
