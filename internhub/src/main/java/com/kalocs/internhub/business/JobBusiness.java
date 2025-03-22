package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


public interface JobBusiness extends BaseBusiness<Job> {
    Page<Job> searchJobs(String jobTitle, String industryId, String jobFunctionId, Pageable pageable);

    int getJobCount(long startDate, long endDate);

    int countJobByCompanyId(UUID id);

    List<Job> getByCompanyId(UUID id);
}
