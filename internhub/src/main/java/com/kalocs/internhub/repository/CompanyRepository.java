package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Company findCompanyById(UUID id);
    List<Company> findAll();
}
