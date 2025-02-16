package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.JobFunctionBusiness;
import com.kalocs.internhub.entity.JobFunction;
import com.kalocs.internhub.model.JobFunctionDTO;
import com.kalocs.internhub.service.JobFunctionService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class JobFunctionServiceImpl implements JobFunctionService {
    private final JobFunctionBusiness jobFunctionBusiness;

    private final ModelMapper modelMapper;

    @Autowired
    public JobFunctionServiceImpl(JobFunctionBusiness jobFunctionBusiness, ModelMapper modelMapper) {
        this.jobFunctionBusiness = jobFunctionBusiness;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobFunctionDTO getJobFunctionById(Integer id) {
        log.debug("getJobFunctionById() JobFunctionServiceImpl start | id: {}", id);
        JobFunction jobFunction = jobFunctionBusiness.getJobFunctionById(id);
        log.debug("getJobFunctionById() JobFunctionServiceImpl end | jobFunction: {}", jobFunction);
        return modelMapper.map(jobFunction, JobFunctionDTO.class);
    }

    @Override
    public List<JobFunctionDTO> getAllJobFunctions() {
        log.debug("getAllJobFunctions() JobFunctionServiceImpl start");
        List<JobFunction> jobFunctionList = jobFunctionBusiness.getAllJobFunctions();
        log.debug("getAllJobFunctions() JobFunctionServiceImpl end");
        return jobFunctionList.stream().map(jobFunction -> modelMapper.map(jobFunction, JobFunctionDTO.class)).toList();
    }
}
