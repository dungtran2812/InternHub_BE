package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.ApplicationBusiness;
import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.business.JobBusiness;
import com.kalocs.internhub.business.RecruiterBusiness;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Application;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.model.CompanyDTO;
import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.payload.request.RecruiterRequest;
import com.kalocs.internhub.payload.response.RecruiterDashboard;
import com.kalocs.internhub.service.RecruiterService;
import com.kalocs.internhub.utils.AuthUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterBusiness recruiterBusiness;
    private final CompanyBusiness companyBusiness;
    private final JobBusiness jobBusiness;
    private final ApplicationBusiness applicationBusiness;
    private final ModelMapper modelMapper;

    @Autowired
    public RecruiterServiceImpl(RecruiterBusiness recruiterBusiness, CompanyBusiness companyBusiness, JobBusiness jobBusiness, ApplicationBusiness applicationBusiness, ModelMapper modelMapper) {
        this.recruiterBusiness = recruiterBusiness;
        this.companyBusiness = companyBusiness;
        this.jobBusiness = jobBusiness;
        this.applicationBusiness = applicationBusiness;
        this.modelMapper = modelMapper;
    }

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

    @Override
    public RecruiterDashboard dashboard() {
        try {
            log.debug("dashboard() RecruiterServiceImpl start |");
            UUID recruiterId = AuthUtils.getCurrentUserId();
            Recruiter recruiter = recruiterBusiness.getRecruiter(recruiterId);
            Company company = recruiter.getCompany();
            if (company == null) {
                throw new AppException(400,"Bạn chưa là nhà tuyển dụng của bất ky công ty nào");
            }
            int jobCount = jobBusiness.countJobByCompanyId(company.getId());
            int applicationCount = applicationBusiness.countApplicationByCompanyId(company.getId());
            RecruiterDashboard dashboard = RecruiterDashboard.builder()
                    .jobCount(jobCount)
                    .applicationCount(applicationCount)
                    .company(modelMapper.map(company, CompanyDTO.class))
                    .build();
            log.debug("dashboard() RecruiterServiceImpl end | {}", dashboard);
            return dashboard;
        } catch (Exception e) {
            log.error("dashboard() RecruiterServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
