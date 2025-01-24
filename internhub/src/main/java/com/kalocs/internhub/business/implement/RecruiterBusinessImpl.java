package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.RecruiterBusiness;
import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RecruiterBusinessImpl implements RecruiterBusiness {

    @Autowired
    private RecruiterRepository recruiterRepository;

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
}
