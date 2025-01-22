package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.JobDTO;
import com.kalocs.internhub.service.JobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstant.JOB)
@Log4j2
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping()
    public ResponseEntity<Page<JobDTO>> getJobsByCategory(
            @RequestParam String categoryId,
            int page, int pageSize) {
        log.info("getJobsByCategory() JobController start | category: {}", categoryId);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<JobDTO> result = jobService.getJobsByCategory(categoryId, pageable);
        log.info("getJobsByCategory() JobController end | {}", result);
        return ResponseEntity.ok().body(result);
    }


}
