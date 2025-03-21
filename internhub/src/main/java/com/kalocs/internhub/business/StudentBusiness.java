package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Student;

public interface StudentBusiness extends BaseBusiness<Student> {
    int getStudentCount(long startDate, long endDate);
}
