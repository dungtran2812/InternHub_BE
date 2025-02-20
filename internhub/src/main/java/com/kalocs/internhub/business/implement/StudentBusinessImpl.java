package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.StudentBusiness;
import com.kalocs.internhub.entity.Student;
import com.kalocs.internhub.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentBusinessImpl extends BaseBusinessImpl<Student, StudentRepository> implements StudentBusiness {
    @Autowired
    public StudentBusinessImpl(StudentRepository repository) {
        super(repository);
    }
}
