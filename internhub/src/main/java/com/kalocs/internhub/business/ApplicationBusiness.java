package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ApplicationBusiness extends BaseBusiness<Application> {
    Page<Application> getByComapnyId(UUID id, Pageable pageable);

    Page<Application> getByStudentId(UUID currentUserId, Pageable pageable);

    int getApplicationCount(long startDate, long endDate);
}
