package com.kalocs.internhub.service;

import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.payload.request.RecruiterRequest;

import java.util.List;
import java.util.UUID;

public interface RecruiterService {
    RecruiterDTO getRecruiter(UUID id);

    List<RecruiterDTO> getAllRecruiters();

    RecruiterDTO createRecruiter(RecruiterRequest recruiter);

    RecruiterDTO updateRecruiter(UUID id, RecruiterRequest recruiter);

    boolean deleteRecruiter(UUID id);
}
