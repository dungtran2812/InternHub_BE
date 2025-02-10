package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.JobFunction;

import java.util.List;

public interface JobFunctionBusiness {
    JobFunction getJobFunctionById(Integer id);
    List<JobFunction> getAllJobFunctions();
}
