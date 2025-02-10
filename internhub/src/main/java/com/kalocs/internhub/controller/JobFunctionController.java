package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.JobFunctionDTO;
import com.kalocs.internhub.service.JobFunctionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLConstant.JOB_FUNCTION)
@Log4j2
@CrossOrigin("*")
public class JobFunctionController {
    @Autowired
    private JobFunctionService jobFunctionService;

    @GetMapping()
    public ResponseEntity<List<JobFunctionDTO>> getAllJobFunctions() {
        log.info("getAllJobFunctions() JobFunctionController start");
        List<JobFunctionDTO> result = jobFunctionService.getAllJobFunctions();
        log.info("getAllJobFunctions() JobFunctionController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobFunctionDTO> getJobFunctionById(@PathVariable Integer id) {
        log.info("getJobFunctionById() JobFunctionController start | id: {}", id);
        JobFunctionDTO result = jobFunctionService.getJobFunctionById(id);
        log.info("getJobFunctionById() JobFunctionController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

}
