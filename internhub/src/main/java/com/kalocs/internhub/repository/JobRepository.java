package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    @Query(value = """
    SELECT * FROM jobs j 
    WHERE (:industryId IS NULL OR j.industry_id = :industryId) 
      AND (:jobFunctionId IS NULL OR j.job_function_id = :jobFunctionId) 
      AND (COALESCE(:jobTitle, '') = '' OR j.search_vector @@ to_tsquery('simple', unaccent(regexp_replace(:jobTitle, '\\s+', ' | ', 'g'))))
    ORDER BY ts_rank(j.search_vector, to_tsquery('simple', unaccent(regexp_replace(:jobTitle, '\\s+', ' | ', 'g')))) DESC
    """,
            countQuery = """
    SELECT COUNT(*) FROM jobs j 
    WHERE (:industryId IS NULL OR j.industry_id = :industryId) 
      AND (:jobFunctionId IS NULL OR j.job_function_id = :jobFunctionId) 
      AND (COALESCE(:jobTitle, '') = '' OR j.search_vector @@ to_tsquery('simple', unaccent(regexp_replace(:jobTitle, '\\s+', ' | ', 'g'))))
    """,
            nativeQuery = true)
    Page<Job> searchJobs(
            @Param("jobTitle") String jobTitle,
            @Param("industryId") Integer industryId,
            @Param("jobFunctionId") Integer jobFunctionId,
            Pageable pageable
    );

}
