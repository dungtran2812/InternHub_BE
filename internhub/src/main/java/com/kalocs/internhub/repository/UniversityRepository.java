package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UniversityRepository extends JpaRepository<University, UUID> {
    University findUniversityById(UUID id);
    List<University> findAll();

}
