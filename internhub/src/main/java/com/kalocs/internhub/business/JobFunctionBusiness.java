package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.JobFunction;

import java.util.Optional;

public interface JobFunctionBusiness {
    Optional<JobFunction> getById(int jobFunctionId);
    List<JobFunction> getAllJobFunctions();
    JobFunction getJobFunctionById(Integer id);
}

