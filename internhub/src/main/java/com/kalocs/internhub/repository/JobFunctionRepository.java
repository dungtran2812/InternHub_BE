package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.JobFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobFunctionRepository extends JpaRepository<JobFunction, Integer> {
    JobFunction findJobFunctionById(Integer id);

    @Override
    List<JobFunction> findAll();
}
