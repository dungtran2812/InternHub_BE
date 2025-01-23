package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.University;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

public interface UniversityBusiness {
    University getUniversity(UUID id);
    List<University> getAllUniversities();
    University createUniversity(University university);

    University updateUniversity(UUID id, University universityToUpdate);

    boolean deleteUniversity(UUID id);
}
