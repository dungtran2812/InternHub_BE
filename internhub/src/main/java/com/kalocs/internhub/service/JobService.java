package com.kalocs.internhub.service;

import com.kalocs.internhub.model.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Page<JobDTO> getJobsByCategory(String categoryId, Pageable pageable);
}
