package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.ApplicationBusiness;
import com.kalocs.internhub.entity.Application;
import com.kalocs.internhub.model.ApplicationDTO;
import com.kalocs.internhub.repository.ApplicationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class ApplicationBusinessImpl extends BaseBusinessImpl<Application, ApplicationRepository> implements ApplicationBusiness {

    public ApplicationBusinessImpl(ApplicationRepository repository) {
        super(repository);
    }

    @Override
    public Page<Application> getByComapnyId(UUID id, Pageable pageable) {
        try {
            log.debug("getByComapnyId() ApplicationBusinessImpl start | id: {}", id);
            Page<Application> result = repository.findByCompanyId(id, pageable);
            log.debug("getByComapnyId() ApplicationBusinessImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getByComapnyId() ApplicationBusinessImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<Application> getByStudentId(UUID studentId, Pageable pageable) {
        try {
            log.debug("getByStudentId() ApplicationBusinessImpl start | currentUserId: {}", studentId);
            Page<Application> result = repository.findByStudentId(studentId, pageable);
            log.debug("getByStudentId() ApplicationBusinessImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getByStudentId() ApplicationBusinessImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
