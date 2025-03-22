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
public class JobBusinessImpl extends BaseBusinessImpl<Job,JobRepository> implements JobBusiness {

    private final JobRepository jobRepository;

    @Autowired
    protected JobBusinessImpl(JobRepository repository, JobRepository jobRepository) {
        super(repository);
        this.jobRepository = jobRepository;
    }

    @Override
    public Page<Job> searchJobs(String jobTitle, String industryId, String jobFunctionId, Pageable pageable) {
        log.debug("searchJob() JobBusinessImpl start | jobTitle: {}, industryId: {}, jobFunctionId: {}", jobTitle, industryId, jobFunctionId);
        Integer industry = null;
        Integer jobFunction = null;
        if (!(industryId == null || industryId.isEmpty())) {
            industry = Integer.parseInt(industryId);
        }
        if (!(jobFunctionId == null || jobFunctionId.isEmpty())) {
            jobFunction = Integer.parseInt(jobFunctionId);
        }
        Page<Job> result = jobRepository.searchJobs(jobTitle, industry, jobFunction, pageable);
        log.debug("searchJob() JobBusinessImpl end");
        return result;
    }

    @Override
    public int getJobCount(long startDate, long endDate) {
        log.debug("getJobCount() JobBusinessImpl start | startDate: {}, endDate: {}", startDate, endDate);
        int result = jobRepository.countByCreatedDateBetween(startDate, endDate);
        log.debug("getJobCount() JobBusinessImpl end | result: {}", result);
        return result;
    }

    @Override
    public int countJobByCompanyId(UUID id) {
        log.debug("countJobByCompanyId() JobBusinessImpl start | id: {}", id);
        int result = jobRepository.countByCompanyId(id);
        log.debug("countJobByCompanyId() JobBusinessImpl end | result: {}", result);
        return result;
    }
}
