package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.JobDTO;
import com.kalocs.internhub.payload.request.CreateJobRequest;
import com.kalocs.internhub.payload.request.JobRequest;
import com.kalocs.internhub.service.JobService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.JOB)
@Log4j2
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("search")
    public ResponseEntity<Page<JobDTO>> searchJob(
            @RequestParam @Nullable String searchText,
            @RequestParam @Nullable String jobFunctionId,
            @RequestParam @Nullable String industryId,
            int page, int pageSize) {
        log.info("searchJob() JobController start | jobTitle: {}, jobFunctionId: {}, industryId: {}", searchText, jobFunctionId, industryId);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<JobDTO> jobs = jobService.searchJob(searchText, jobFunctionId, industryId, pageable);
        log.info("searchJob() JobController end | {}", jobs);
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable UUID id) {
        log.info("getJob() JobController start | id: {}", id);
        JobDTO job = jobService.getJob(id);
        log.info("getJob() JobController end | {}", job);
        return ResponseEntity.ok().body(job);
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        log.info("getAllJobs() JobController start");
        List<JobDTO> jobs = jobService.getAllJobs();
        log.info("getAllJobs() JobController end");
        return ResponseEntity.ok().body(jobs);
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobRequest job) {
        log.info("createJob() JobController start | {}", job);
        JobDTO createdJob = jobService.createJob(job);
        log.info("createJob() JobController end | {}", createdJob);
        return ResponseEntity.ok().body(createdJob);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable UUID id, @RequestBody JobRequest job) {
        log.info("updateJob() JobController start | {}", job);
        JobDTO updatedJob = jobService.updateJob(id, job);
        log.info("updateJob() JobController end | {}", updatedJob);
        return ResponseEntity.ok().body(updatedJob);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJob(@PathVariable UUID id) {
        log.info("deleteJob() JobController start | {}", id);
        boolean check = jobService.deleteJob(id);

        if (check) {
            log.info("deleteJob() JobController end | Deleted");
            return ResponseEntity.ok().body("Job deleted successfully");
        } else {
            log.info("deleteJob() JobController end | Not found");
            return ResponseEntity.ok().body("Job not found");
        }
    }

    @PostMapping("create")
    @Operation(summary = "Create job", description = "Recruiter creates job")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<JobDTO> createJob(@RequestBody CreateJobRequest jobRequest) {
        log.info("createJob() JobController by recruiter start | jobRequest: {}", jobRequest);
        JobDTO job = jobService.createJob(jobRequest);
        log.info("createJob() JobController by recruiter end | {}", job);
        return ResponseEntity.ok().body(job);
    }

    @PostMapping("edit/{id}")
    @Operation(summary = "Update job", description = "Recruiter updates job")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<JobDTO> editJob(@RequestBody CreateJobRequest jobRequest, @PathVariable String id) {
        log.info("editJob() JobController by recruiter start | jobRequest: {}", jobRequest);
        JobDTO job = jobService.editJob(jobRequest, id);
        log.info("editJob() JobController by recruiter end | {}", job);
        return ResponseEntity.ok().body(job);
    }

    @GetMapping("recruiter-get-jobs")
    @Operation(summary = "Get jobs by recruiter", description = "Recruiter gets jobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<List<JobDTO>> getJobsByRecruiter() {
        log.info("getJobsByRecruiter() JobController start");
        List<JobDTO> jobs = jobService.getJobsByRecruiter();
        log.info("getJobsByRecruiter() JobController end | {}", jobs);
        return ResponseEntity.ok().body(jobs);
    }

}
