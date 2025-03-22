package com.kalocs.internhub.service;

import com.kalocs.internhub.model.JobDTO;
import com.kalocs.internhub.payload.request.CreateJobRequest;
import com.kalocs.internhub.payload.request.JobRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface JobService {

    JobDTO getJob(UUID id);

    List<JobDTO> getAllJobs();

    JobDTO createJob(JobRequest job);

    JobDTO updateJob(UUID id, JobRequest job);

    boolean deleteJob(UUID id);

    Page<JobDTO> searchJob(String searchText, String jobFunctionId, String industryId, Pageable pageable);

    JobDTO createJob(CreateJobRequest jobRequest);

    JobDTO editJob(CreateJobRequest jobRequest, String id);

    List<JobDTO> getJobsByRecruiter();
}
