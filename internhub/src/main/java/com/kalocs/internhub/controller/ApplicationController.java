package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.ApplicationStatus;
import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.ApplicationDTO;
import com.kalocs.internhub.payload.request.ApplicationRequest;
import com.kalocs.internhub.payload.request.ApplyJobRequest;
import com.kalocs.internhub.payload.response.ResponseMessage;
import com.kalocs.internhub.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.APPLICATION)
@Log4j2
@CrossOrigin("*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        log.info("getAllApplications() ApplicationController start");
        List<ApplicationDTO> result = applicationService.getAllApplications();
        log.info("getAllApplications() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable UUID id) {
        log.info("getApplicationById() ApplicationController start | id: {}", id);
        ApplicationDTO result = applicationService.getApplicationById(id);
        log.info("getApplicationById() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationRequest application) {
        log.info("createApplication() ApplicationController start | applicationDTO: {}", application);
        ApplicationDTO result = applicationService.createApplication(application);
        log.info("createApplication() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable UUID id, @RequestBody ApplicationRequest application) {
        log.info("updateApplication() ApplicationController start | applicationDTO: {}", application);
        ApplicationDTO result = applicationService.updateApplication(id, application);
        log.info("updateApplication() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteApplication(@PathVariable UUID id) {
        log.info("deleteApplication() ApplicationController start | id: {}", id);
        boolean check = applicationService.deleteApplication(id);
        log.info("deleteApplication() ApplicationController end | id: {}", id);
        if (!check) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseMessage(false,"Không thể xóa"));
        }
        return ResponseEntity.ok().body(ResponseMessage.builder().message("Xóa thành công").success(true).build());
    }

    @PostMapping("apply-job")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Apply job", description = "Student apply job")
    public ResponseEntity<ApplicationDTO> applyJob(@RequestBody ApplyJobRequest applicationRequest) {
        log.info("applyJob() ApplicationController start | applicationRequest: {}", applicationRequest);
        ApplicationDTO applyJob = applicationService.applyJob(applicationRequest);
        log.info("applyJob() ApplicationController end | applicationRequest: {}", applicationRequest);
        return ResponseEntity.ok().body(applyJob);
    }

    @PutMapping("{id}/update-status")
    @PreAuthorize("hasRole('RECRUITER')")
    @Operation(summary = "Update application status", description = "Recruiter update application status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(@PathVariable String id, @RequestParam ApplicationStatus status) {
        log.info("updateApplicationStatus() ApplicationController start | id: {}", id);
        ApplicationDTO result = applicationService.updateStatus(id,status);
        log.info("updateApplicationStatus() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("recruiter-get-application")
    @PreAuthorize("hasRole('RECRUITER')")
    @Operation(summary = "Get application by recruiter", description = "Recruiter get application by recruiter")
    public ResponseEntity<Page<ApplicationDTO>> getApplicationByRecruiter(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int pageSize,
                                                                          @RequestParam(defaultValue = "0") String order) {
        log.info("getApplicationByRecruiter() ApplicationController start");
        Page<ApplicationDTO> result = applicationService.getApplicationByRecruiter(page, pageSize, order);
        log.info("getApplicationByRecruiter() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    //Student get application
    @GetMapping("student-get-application")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get application by student", description = "Student get application by student")
    public ResponseEntity<Page<ApplicationDTO>> getApplicationByStudent(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                                      @RequestParam(defaultValue = "0") String order) {
        log.info("getApplicationByStudent() ApplicationController start");
        Page<ApplicationDTO> result = applicationService.getApplicationByStudent(page, pageSize, order);
        log.info("getApplicationByStudent() ApplicationController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    //Student delete application
    @DeleteMapping("student-delete-application/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Delete application by student", description = "Student delete application by student")
    public ResponseEntity<ResponseMessage> deleteApplicationByStudent(@PathVariable UUID id) {
        log.info("deleteApplicationByStudent() ApplicationController start | id: {}", id);
        boolean check = applicationService.deleteApplicationByStudent(id);
        log.info("deleteApplicationByStudent() ApplicationController end | id: {}", id);
        if (!check) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseMessage(false,"Không thể xóa"));
        }
        return ResponseEntity.ok().body(ResponseMessage.builder().message("Xóa thành công").success(true).build());
    }
}
