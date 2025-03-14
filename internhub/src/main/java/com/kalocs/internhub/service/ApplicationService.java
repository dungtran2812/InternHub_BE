package com.kalocs.internhub.service;

import com.kalocs.internhub.model.ApplicationDTO;
import com.kalocs.internhub.payload.request.ApplicationRequest;
import com.kalocs.internhub.payload.request.ApplyJobRequest;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    List<ApplicationDTO> getAllApplications();

    ApplicationDTO getApplicationById(UUID id);

    ApplicationDTO createApplication(ApplicationRequest application);

    ApplicationDTO updateApplication(UUID id, ApplicationRequest application);

    boolean deleteApplication(UUID id);

    ApplicationDTO applyJob(ApplyJobRequest applicationRequest);
}
