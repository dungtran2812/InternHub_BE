package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.JobFunctionBusiness;
import com.kalocs.internhub.entity.JobFunction;
import com.kalocs.internhub.repository.JobFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobFunctionBusinessImpl implements JobFunctionBusiness {

    private final JobFunctionRepository jobFunctionRepository;

    @Autowired
    public JobFunctionBusinessImpl(JobFunctionRepository jobFunctionRepository) {
        this.jobFunctionRepository = jobFunctionRepository;
    }

    @Override
    public Optional<JobFunction> getById(int jobFunctionId) {
        return jobFunctionRepository.findById(jobFunctionId);
    }
    
    @Override
    public JobFunction getJobFunctionById(Integer id) {
        return jobFunctionRepository.findJobFunctionById(id);
    }

    @Override
    public List<JobFunction> getAllJobFunctions() {
        return jobFunctionRepository.findAll();
    }
}
