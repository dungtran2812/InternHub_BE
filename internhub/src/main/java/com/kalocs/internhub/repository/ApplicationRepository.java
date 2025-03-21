package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {

    @Query("SELECT a FROM Application a WHERE a.job.company.id = :id")
    Page<Application> findByCompanyId(UUID id, Pageable pageable);

    Page<Application> findByStudentId(UUID studentId, Pageable pageable);

    int countByCreatedDateBetween(long startDate, long endDate);
}
