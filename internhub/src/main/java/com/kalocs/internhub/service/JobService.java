package com.kalocs.internhub.service;

import com.kalocs.internhub.model.JobDTO;
import com.kalocs.internhub.payload.request.JobRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface JobService {
    Page<JobDTO> getJobsByCategory(String categoryId, Pageable pageable);

    JobDTO getJob(UUID id);

    List<JobDTO> getAllJobs();

    JobDTO createJob(JobRequest job);

    JobDTO updateJob(UUID id, JobRequest job);

    boolean deleteJob(UUID id);
}
