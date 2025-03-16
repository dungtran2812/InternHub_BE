package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.ApplicationBusiness;
import com.kalocs.internhub.business.JobBusiness;
import com.kalocs.internhub.business.RecruiterBusiness;
import com.kalocs.internhub.business.StudentBusiness;
import com.kalocs.internhub.common.ApplicationStatus;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Application;
import com.kalocs.internhub.entity.Job;
import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.entity.Student;
import com.kalocs.internhub.model.ApplicationDTO;
import com.kalocs.internhub.payload.request.ApplicationRequest;
import com.kalocs.internhub.payload.request.ApplyJobRequest;
import com.kalocs.internhub.service.ApplicationService;
import com.kalocs.internhub.utils.AuthUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationBusiness applicationBusiness;
    private final StudentBusiness studentBusiness;
    private final JobBusiness jobBusiness;
    private final RecruiterBusiness recruiterBusiness;
    private final ModelMapper modelMapper;

    @Autowired
    public ApplicationServiceImpl(ApplicationBusiness applicationBusiness, StudentBusiness studentBusiness, JobBusiness jobBusiness, RecruiterBusiness recruiterBusiness, ModelMapper modelMapper) {
        this.applicationBusiness = applicationBusiness;
        this.studentBusiness = studentBusiness;
        this.jobBusiness = jobBusiness;
        this.recruiterBusiness = recruiterBusiness;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        try {
            log.debug("getAllApplications() ApplicationServiceImpl start");
            List<ApplicationDTO> result = applicationBusiness.getAll().stream()
                    .map(application ->modelMapper.map(application, ApplicationDTO.class)).toList();
            log.debug("getAllApplications() ApplicationServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getAllApplications() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ApplicationDTO getApplicationById(UUID id) {
        try {
            log.debug("getApplicationById() ApplicationServiceImpl start | id: {}", id);
            ApplicationDTO result = modelMapper.map(applicationBusiness.getById(id), ApplicationDTO.class);
            log.debug("getApplicationById() ApplicationServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getApplicationById() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ApplicationDTO createApplication(ApplicationRequest application) {
        try {
            log.debug("createApplication() ApplicationServiceImpl start | applicationDTO: {}", application);
            Student student = studentBusiness.getById(application.getStudentId()).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy sinh viên"));
            Job job = jobBusiness.getById(application.getJobId()).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy công việc"));
            Application newApplication = new Application();
            newApplication.setStudent(student);
            newApplication.setJob(job);
            newApplication.setId(UUID.randomUUID());
            newApplication.setResume(student.getResume());
            newApplication.setDate(Instant.now().toEpochMilli());
            newApplication.setStatus(ApplicationStatus.PENDING);
            ApplicationDTO result = modelMapper.map(applicationBusiness.create(newApplication), ApplicationDTO.class);
            log.debug("createApplication() ApplicationServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("createApplication() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ApplicationDTO updateApplication(UUID id, ApplicationRequest application) {
        try {
            log.debug("updateApplication() ApplicationServiceImpl start | id: {}, applicationDTO: {}", id, application);
            Application newApplication = applicationBusiness.getById(id).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy ứng tuyển"));
            Student student = studentBusiness.getById(application.getStudentId()).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy sinh viên"));
            Job job = jobBusiness.getById(application.getJobId()).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy công việc"));
            newApplication.setStudent(student);
            newApplication.setJob(job);
            newApplication.setId(UUID.randomUUID());
            newApplication.setResume(student.getResume());
            newApplication.setDate(Instant.now().toEpochMilli());
            newApplication.setStatus(ApplicationStatus.PENDING);
            ApplicationDTO result = modelMapper.map(applicationBusiness.update(newApplication), ApplicationDTO.class);
            log.debug("updateApplication() ApplicationServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("updateApplication() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }


    @Override
    public boolean deleteApplication(UUID id) {
        try {
            log.debug("deleteApplication() ApplicationServiceImpl start | id: {}", id);
            if (!applicationBusiness.existsById(id)) {
                throw new AppException(404, "Không tìm thấy ứng tuyển");
            }
            applicationBusiness.delete(id);
            log.debug("deleteApplication() ApplicationServiceImpl end | id: {}", id);
            return true;
        } catch (Exception e) {
            log.error("deleteApplication() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ApplicationDTO applyJob(ApplyJobRequest applicationRequest) {
        try {
            log.debug("applyJob() ApplicationServiceImpl start | applicationRequest: {}", applicationRequest);
            Student student = studentBusiness.getById(AuthUtils.getCurrentUserId()).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy sinh viên"));
            Job job = jobBusiness.getById(UUID.fromString(applicationRequest.getJobId())).orElseThrow(() ->new AppException(404, "Không tìm thấy công việc"));
            Application newApplication = new Application();
            newApplication.setStudent(student);
            newApplication.setJob(job);
            newApplication.setId(UUID.randomUUID());
            newApplication.setResume(applicationRequest.getResume());
            newApplication.setCoverLetter(applicationRequest.getCoverLetter());
            newApplication.setDate(Instant.now().toEpochMilli());
            newApplication.setStatus(ApplicationStatus.PENDING);
            ApplicationDTO result = modelMapper.map(applicationBusiness.create(newApplication), ApplicationDTO.class);
            log.debug("applyJob() ApplicationServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("applyJob() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ApplicationDTO approveApplication(String id) {
        try {
            log.debug("approveApplication() ApplicationServiceImpl start | id: {}", id);
            Application application = applicationBusiness.getById(UUID.fromString(id)).orElseThrow(() ->
                    new AppException(404, "Không tìm thấy ứng tuyển"));
            Recruiter recruiter = recruiterBusiness.getRecruiter(AuthUtils.getCurrentUserId());
            if (recruiter == null) {
                throw new AppException(404, "Không tìm thấy nhà tuyển dụng");
            }
            if (!application.getJob().getCompany().getId().equals(recruiter.getCompany().getId())) {
                throw new AppException(HttpStatus.FORBIDDEN.value(), "Không thể duyệt ứng tuyển của công ty khác");
            }
            application.setStatus(ApplicationStatus.ACCEPT);
            ApplicationDTO result = modelMapper.map(applicationBusiness.update(application), ApplicationDTO.class);
            log.debug("approveApplication() ApplicationServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("approveApplication() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<ApplicationDTO> getApplicationByRecruiter(int page, int pageSize, String order) {
        try {
            log.debug("getApplicationByRecruiter() ApplicationServiceImpl start");
            Recruiter recruiter = recruiterBusiness.getRecruiter(AuthUtils.getCurrentUserId());
            if (recruiter == null) {
                throw new AppException(404, "Không tìm thấy nhà tuyển dụng");
            }
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(order), "createdDate"));
            Page<Application> result = applicationBusiness.getByComapnyId(recruiter.getCompany().getId(), pageable);
            Page<ApplicationDTO> resultDTO = result.map(application -> modelMapper.map(application, ApplicationDTO.class));
            log.debug("getApplicationByRecruiter() ApplicationServiceImpl end | {}", result);
            return resultDTO;
        } catch (Exception e) {
            log.error("getApplicationByRecruiter() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<ApplicationDTO> getApplicationByStudent(int page, int pageSize, String order) {
        try {
            log.debug("getApplicationByStudent() ApplicationServiceImpl start");
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(order), "createdDate"));
            Page<Application> result = applicationBusiness.getByStudentId(AuthUtils.getCurrentUserId(), pageable);
            Page<ApplicationDTO> resultDTO = result.map(application -> modelMapper.map(application, ApplicationDTO.class));
            log.debug("getApplicationByStudent() ApplicationServiceImpl end | {}", result);
            return resultDTO;
        } catch (Exception e) {
            log.error("getApplicationByStudent() ApplicationServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
