package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.business.RecruiterBusiness;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.payload.request.RecruiterRequest;
import com.kalocs.internhub.service.RecruiterService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private RecruiterBusiness recruiterBusiness;
    @Autowired
    private CompanyBusiness companyBusiness;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecruiterDTO getRecruiter(UUID id) {
        try {
            log.debug("getRecruiter() RecruiterServiceImpl start | {}", id);
            RecruiterDTO recruiter = modelMapper.map(recruiterBusiness.getRecruiter(id), RecruiterDTO.class);
            log.debug("getRecruiter() RecruiterServiceImpl end | {}", recruiter);
            return recruiter;
        } catch (Exception e) {
            log.error("getRecruiter() RecruiterServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<RecruiterDTO> getAllRecruiters() {
        try {
            log.debug("getAllRecruiters() RecruiterServiceImpl start");
            List<RecruiterDTO> recruiters = recruiterBusiness.getAllRecruiters().stream()
                    .map(recruiter -> modelMapper.map(recruiter, RecruiterDTO.class)).toList();
            log.debug("getAllRecruiters() RecruiterServiceImpl end");
            return recruiters;
        } catch (Exception e) {
            log.error("getAllRecruiters() RecruiterServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public RecruiterDTO createRecruiter(RecruiterRequest recruiter) {
        try {
            log.debug("createRecruiter() RecruiterServiceImpl start | {}", recruiter);
            Recruiter recruiterToCreate = modelMapper.map(recruiter, Recruiter.class);
            Company company = companyBusiness.getCompany(UUID.fromString(recruiter.getCompanyId()));
            if (company == null) {
                throw new AppException(404,"Company not found");
            }
            recruiterToCreate.setCompany(company);
            recruiterToCreate.setId(UUID.randomUUID());
            RecruiterDTO createdRecruiter = modelMapper.map(recruiterBusiness.createRecruiter(recruiterToCreate), RecruiterDTO.class);
            log.debug("createRecruiter() RecruiterServiceImpl end | {}", createdRecruiter);
            return createdRecruiter;
        } catch (Exception e) {
            log.error("createRecruiter() RecruiterServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public RecruiterDTO updateRecruiter(UUID id, RecruiterRequest recruiter) {
        try {
            log.debug("updateRecruiter() RecruiterServiceImpl start | {}", recruiter);
            Recruiter recruiterToUpdate = modelMapper.map(recruiter, Recruiter.class);
            Company company = companyBusiness.getCompany(UUID.fromString(recruiter.getCompanyId()));
            if (company == null) {
                throw new AppException(404,"Company not found");
            }
            recruiterToUpdate.setCompany(company);
            recruiterToUpdate.setId(id);
            RecruiterDTO updatedRecruiter = modelMapper.map(recruiterBusiness.updateRecruiter(recruiterToUpdate), RecruiterDTO.class);
            log.debug("updateRecruiter() RecruiterServiceImpl end | {}", updatedRecruiter);
            return updatedRecruiter;
        } catch (Exception e) {
            log.error("updateRecruiter() RecruiterServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteRecruiter(UUID id) {
        try {
            log.debug("deleteRecruiter RecruiterServiceImpl start | {}", id);
            boolean check = recruiterBusiness.deleteRecruiter(id);
            log.debug("deleteRecruiter RecruiterServiceImpl end | {}", id);
            return check;
        } catch (Exception e) {
            log.error("deleteRecruiter RecruiterServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
