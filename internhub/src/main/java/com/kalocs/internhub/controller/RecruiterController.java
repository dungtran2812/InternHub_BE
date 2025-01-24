package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.payload.request.RecruiterRequest;
import com.kalocs.internhub.service.RecruiterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.RECRUITER)
@Log4j2
@CrossOrigin("*")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("{id}")
    public ResponseEntity<RecruiterDTO> getRecruiter(@PathVariable UUID id) {
        log.info("getRecruiter() RecruiterController Start | " + id);
        RecruiterDTO recruiter = recruiterService.getRecruiter(id);
        log.info("getRecruiter() RecruiterController End | " + recruiter);
        return ResponseEntity.ok().body(recruiter);
    }

    @GetMapping
    public ResponseEntity<List<RecruiterDTO>> getAllRecruiter() {
        log.info("getAllRecruiter() RecruiterController Start |");
        List<RecruiterDTO> recruiters = recruiterService.getAllRecruiters();
        log.info("getAllRecruiter() RecruiterController End |");
        return ResponseEntity.ok().body(recruiters);
    }

    @PostMapping
    public ResponseEntity<RecruiterDTO> createRecruiter(@RequestBody RecruiterRequest recruiter) {
        log.info("createRecruiter() RecruiterController Start | {}", recruiter);
        RecruiterDTO createdRecruiter = recruiterService.createRecruiter(recruiter);
        log.info("createRecruiter() RecruiterController End | {}", createdRecruiter);
        return ResponseEntity.ok().body(createdRecruiter);
    }

    @PutMapping("{id}")
    public ResponseEntity<RecruiterDTO> updateRecruiter(@PathVariable UUID id, @RequestBody RecruiterRequest recruiter) {
        log.info("updateRecruiter() RecruiterController Start | {}", recruiter);
        RecruiterDTO updatedRecruiter = recruiterService.updateRecruiter(id, recruiter);
        log.info("updateRecruiter() RecruiterController End | {}", updatedRecruiter);
        return ResponseEntity.ok().body(updatedRecruiter);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable UUID id) {
        log.info("deleteRecruiter() RecruiterController Start | " + id);
        boolean check = recruiterService.deleteRecruiter(id);

        if (check) {
            log.info("deleteRecruiter() RecruiterController End | Recruiter deleted");
            return ResponseEntity.ok().body("Recruiter deleted");
        } else {
            log.info("deleteRecruiter() RecruiterController End | Recruiter not deleted");
            return ResponseEntity.ok().body("Cannot delete Recruiter");
        }
    }


}
