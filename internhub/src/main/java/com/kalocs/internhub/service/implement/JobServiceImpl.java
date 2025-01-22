package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.JobBusiness;
import com.kalocs.internhub.entity.Job;
import com.kalocs.internhub.model.JobDTO;
import com.kalocs.internhub.service.JobService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class JobServiceImpl implements JobService {

    @Autowired
    private JobBusiness jobBusiness;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<JobDTO> getJobsByCategory(String categoryId, Pageable pageable) {
        log.debug("getJobsByCategory() JobServiceImpl start | category: {}", categoryId);
        Page<Job> jobList = jobBusiness.getJobsByCategory(UUID.fromString(categoryId), pageable);
        List<JobDTO> jobDTOList = jobList.map(job -> modelMapper.map(job, JobDTO.class)).getContent();
        Page<JobDTO> result = new PageImpl<>(jobDTOList, jobList.getPageable(), jobList.getTotalElements());
        log.debug("getJobsByCategory() JobServiceImpl end");
        return result;
    }
}
