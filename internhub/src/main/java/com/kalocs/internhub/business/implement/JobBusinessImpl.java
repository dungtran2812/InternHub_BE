package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.JobBusiness;
import com.kalocs.internhub.entity.Job;
import com.kalocs.internhub.repository.JobRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class JobBusinessImpl implements JobBusiness {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Page<Job> getJobsByCategory(UUID categoryId, Pageable pageable) {
        log.debug("getJobsByCategory() JobBusinessImpl start | category: {}", categoryId);
        Page<Job> result = jobRepository.findByCategoryId(categoryId, pageable);
        log.debug("getJobsByCategory() JobBusinessImpl end");
        return result;
    }
}
