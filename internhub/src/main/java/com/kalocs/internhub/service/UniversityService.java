package com.kalocs.internhub.service;

import com.kalocs.internhub.entity.University;
import com.kalocs.internhub.model.UniversityDTO;
import com.kalocs.internhub.payload.request.UniversityRequest;

import java.util.List;
import java.util.UUID;
public interface UniversityService {
    University getUniversity(UUID id);
    List<UniversityDTO> getAllUniversities();

    UniversityDTO createUniversity(UniversityRequest university);

    UniversityDTO updateUniversity(UUID id, UniversityRequest university);

    boolean deleteUniversity(UUID id);
}
