package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer> {
    Industry findIndustryById(Integer id);

    @Override
    List<Industry> findAll();
}
