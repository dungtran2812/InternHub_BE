package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.RecruiterBusiness;
import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.repository.RecruiterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class RecruiterBusinessImpl implements RecruiterBusiness {

    private final RecruiterRepository recruiterRepository;

    @Autowired
    public RecruiterBusinessImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Recruiter getRecruiter(UUID id) {
        return recruiterRepository.findById(id).orElse(null);
    }

    @Override
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @Override
    public Recruiter createRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public Recruiter updateRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public boolean deleteRecruiter(UUID id) {
        try {
            recruiterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getRecruiterCount(long startDate, long endDate) {
        try {
            log.debug("getRecruiterCount() RecruiterBusinessImpl start | startDate: {}, endDate: {}", startDate, endDate);
            int result = recruiterRepository.countByCreatedDateBetween(startDate, endDate);
            log.debug("getRecruiterCount() RecruiterBusinessImpl end | result: {}", result);
            return result;
        } catch (Exception e) {
            return 0;
        }
    }
}
