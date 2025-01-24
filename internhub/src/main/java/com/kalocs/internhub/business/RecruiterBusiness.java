package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.model.RecruiterDTO;

import java.util.List;
import java.util.UUID;

public interface RecruiterBusiness {
    Recruiter getRecruiter(UUID id);

    List<Recruiter> getAllRecruiters();

    Recruiter createRecruiter(Recruiter recruiter);

    Recruiter updateRecruiter(Recruiter recruiter);

    boolean deleteRecruiter(UUID id);
}
