package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface JobBusiness {
    Page<Job> getJobsByCategory(UUID categoryId, Pageable pageable);
}
