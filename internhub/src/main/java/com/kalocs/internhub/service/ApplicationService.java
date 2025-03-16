package com.kalocs.internhub.service;

import com.kalocs.internhub.model.ApplicationDTO;
import com.kalocs.internhub.payload.request.ApplicationRequest;
import com.kalocs.internhub.payload.request.ApplyJobRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    List<ApplicationDTO> getAllApplications();

    ApplicationDTO getApplicationById(UUID id);

    ApplicationDTO createApplication(ApplicationRequest application);

    ApplicationDTO updateApplication(UUID id, ApplicationRequest application);

    boolean deleteApplication(UUID id);

    ApplicationDTO applyJob(ApplyJobRequest applicationRequest);

    ApplicationDTO approveApplication(String id);

    Page<ApplicationDTO> getApplicationByRecruiter(int page, int pageSize, String order);

    Page<ApplicationDTO> getApplicationByStudent(int page, int pageSize, String order);

    boolean deleteApplicationByStudent(UUID id);
}
