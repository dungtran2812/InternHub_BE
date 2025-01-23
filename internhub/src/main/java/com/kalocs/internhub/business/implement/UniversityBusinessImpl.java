package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.UniversityBusiness;
import com.kalocs.internhub.entity.University;
import com.kalocs.internhub.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UniversityBusinessImpl implements UniversityBusiness {
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityBusinessImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University getUniversity(UUID id) {
        return universityRepository.findUniversityById(id);
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(UUID id, University universityToUpdate) {
        return universityRepository.save(universityToUpdate);
    }

    @Override
    public boolean deleteUniversity(UUID id) {
        universityRepository.deleteById(id);
        return true;
    }
}
