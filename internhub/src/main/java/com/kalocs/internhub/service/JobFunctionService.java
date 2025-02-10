package com.kalocs.internhub.service;

import com.kalocs.internhub.model.JobFunctionDTO;

import java.util.List;

public interface JobFunctionService {
    JobFunctionDTO getJobFunctionById(Integer id);
    List<JobFunctionDTO> getAllJobFunctions();
}
