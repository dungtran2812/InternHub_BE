package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.StudentBusiness;
import com.kalocs.internhub.entity.Student;
import com.kalocs.internhub.repository.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StudentBusinessImpl extends BaseBusinessImpl<Student, StudentRepository> implements StudentBusiness {
    @Autowired
    public StudentBusinessImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public int getStudentCount(long startDate, long endDate) {
        try {
            log.debug("getStudentCount() StudentBusinessImpl start | startDate: {}, endDate: {}", startDate, endDate);
            int result = repository.countByCreatedDateBetween(startDate, endDate);
            log.debug("getStudentCount() StudentBusinessImpl end | result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("getStudentCount() StudentBusinessImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
