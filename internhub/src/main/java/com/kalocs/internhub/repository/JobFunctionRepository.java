package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.JobFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFunctionRepository extends JpaRepository<JobFunction, Integer> {
}
