package com.kalocs.internhub.service.implement;

import ch.qos.logback.core.util.StringUtil;
import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.business.IndustryBusiness;
import com.kalocs.internhub.business.JobBusiness;
import com.kalocs.internhub.business.JobFunctionBusiness;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.entity.Industry;
import com.kalocs.internhub.entity.Job;
import com.kalocs.internhub.entity.JobFunction;
import com.kalocs.internhub.model.JobDTO;
import com.kalocs.internhub.payload.request.JobRequest;
import com.kalocs.internhub.service.JobService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class JobServiceImpl implements JobService {

    private final JobBusiness jobBusiness;
    private final CompanyBusiness companyBusiness;
    private final IndustryBusiness industryBusiness;
    private final JobFunctionBusiness jobFunctionBusiness;
    private final ModelMapper modelMapper;

    @Autowired
    public JobServiceImpl(JobBusiness jobBusiness, CompanyBusiness companyBusiness, IndustryBusiness industryBusiness, JobFunctionBusiness jobFunctionBusiness, ModelMapper modelMapper) {
        this.jobBusiness = jobBusiness;
        this.companyBusiness = companyBusiness;
        this.industryBusiness = industryBusiness;
        this.jobFunctionBusiness = jobFunctionBusiness;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobDTO getJob(UUID id) {
        try {
            log.debug("getJob() JobServiceImpl start | {}", id);
            JobDTO job = modelMapper.map(jobBusiness.getById(id).orElseThrow(), JobDTO.class);
            log.debug("getJob() JobServiceImpl end | {}", job);
            return job;
        } catch (Exception e) {
            log.error("getJob() JobServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<JobDTO> getAllJobs() {
        try {
            log.debug("getAllJobs() JobServiceImpl start");
            List<JobDTO> jobs = jobBusiness.getAll().stream()
                    .map(job -> modelMapper.map(job, JobDTO.class)).toList();
            log.debug("getAllJobs() JobServiceImpl end");
            return jobs;
        } catch (Exception e) {
            log.error("getAllJobs() JobServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public JobDTO createJob(JobRequest job) {
        try {
            log.debug("createJob() JobServiceImpl start | {}", job);
            Job jobToCreate = modelMapper.map(job, Job.class);
            jobToCreate.setId(UUID.randomUUID());
            // Check if company exists
            if (job.getCompanyId() == null) {
                log.debug("createJob() JobServiceImpl end | null");
                throw new AppException(400, "Company Id is required to create Job");
            }
            Company company = companyBusiness.getById(job.getCompanyId()).orElseThrow(() -> new AppException(404, "Cannot find Company to create Job with id: " + job.getCompanyId().toString()));
            jobToCreate.setCompany(company);

            // Check if industry exists
            if (!(job.getIndustryId() == null || job.getIndustryId().isEmpty())) {
                Industry industry = industryBusiness.getById(Integer.parseInt(job.getIndustryId())).orElseThrow(() -> new AppException(404, "Cannot find Industry to create Job with id: " + job.getIndustryId()));
                jobToCreate.setIndustry(industry);
            }

            // Check if job function exists
            if (!(job.getJobFunctionId() == null || job.getJobFunctionId().isEmpty())) {
                JobFunction jobFunction = jobFunctionBusiness.getById(Integer.parseInt(job.getJobFunctionId())).orElseThrow(() -> new AppException(404, "Cannot find JobFunction to create Job with id: " + job.getJobFunctionId()));
                jobToCreate.setJobFunction(jobFunction);
            }

            JobDTO createdJob = modelMapper.map(jobBusiness.create(modelMapper.map(jobToCreate, Job.class)), JobDTO.class);
            log.debug("createJob() JobServiceImpl end | {}", createdJob);
            return createdJob;
        } catch (Exception e) {
            log.error("createJob() JobServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public JobDTO updateJob(UUID id, JobRequest job) {
        try {
            log.debug("updateJob() JobServiceImpl start | {}", job);
            Job jobToSource = jobBusiness.getById(id).orElseThrow(() -> new AppException(404, "Cannot find Job to update with id: " + id.toString()));
            Job jobToUpdate = modelMapper.map(job, Job.class);
            jobToUpdate.setId(id);
            jobToUpdate.setCompany(jobToSource.getCompany());
            // Check if industry exists
            if (!(job.getIndustryId() == null || job.getIndustryId().isEmpty())) {
                Industry industry = industryBusiness.getById(Integer.parseInt(job.getIndustryId())).orElseThrow(() -> new AppException(404, "Cannot find Industry to update Job with id: " + job.getIndustryId()));
                jobToUpdate.setIndustry(industry);
            }

            // Check if job function exists
            if (!(job.getJobFunctionId() == null || job.getJobFunctionId().isEmpty())) {
                JobFunction jobFunction = jobFunctionBusiness.getById(Integer.parseInt(job.getJobFunctionId())).orElseThrow(() -> new AppException(404, "Cannot find JobFunction to update Job with id: " + job.getJobFunctionId()));
                jobToUpdate.setJobFunction(jobFunction);
            }

            JobDTO updatedJob = modelMapper.map(jobBusiness.update(modelMapper.map(jobToUpdate, Job.class)), JobDTO.class);
            log.debug("updateJob() JobServiceImpl end | {}", updatedJob);
            return updatedJob;
        } catch (Exception e) {
            log.error("updateJob() JobServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteJob(UUID id) {
        try {
            log.debug("deleteJob() JobServiceImpl start | {}", id);
            if (jobBusiness.getById(id).isEmpty()) {
                log.debug("deleteJob() JobServiceImpl end | false");
                throw new AppException(404, "Cannot find Job to delete with id: " + id.toString());
            }
            boolean check = jobBusiness.delete(id);
            log.debug("deleteJob() JobServiceImpl end | {}", check);
            return check;
        } catch (Exception e) {
            log.error("deleteJob() JobServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<JobDTO> searchJob(String searchText, String jobFunctionId, String industryId, Pageable pageable) {
        try {
            log.debug("searchJob() JobServiceImpl start | jobTitle: {}, jobFunctionId: {}, industryId: {}", searchText, jobFunctionId, industryId);
            if (searchText != null) {
                searchText = searchText.trim();
            }
            Page<Job> jobList = jobBusiness.searchJobs(searchText,industryId,jobFunctionId,pageable);
            List<JobDTO> jobDTOList = jobList.map(job -> modelMapper.map(job, JobDTO.class)).getContent();
            Page<JobDTO> result = new PageImpl<>(jobDTOList, jobList.getPageable(), jobList.getTotalElements());
            log.debug("searchJob() JobServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("searchJob() JobServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
