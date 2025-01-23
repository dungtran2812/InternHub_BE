package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.entity.University;
import com.kalocs.internhub.model.UniversityDTO;
import com.kalocs.internhub.payload.request.UniversityRequest;
import com.kalocs.internhub.service.UniversityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.UNIVERSITY)
@Log4j2
@CrossOrigin("*")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("{id}")
    public ResponseEntity<University> getUniversity(@PathVariable UUID id) {
        log.info("getUniversity() UniversityController start");
        University result = universityService.getUniversity(id);
        log.info("getUniversity() UniversityController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<UniversityDTO>> getAllUniversities() {
        log.info("getAllUniversities() UniversityController start");
        List<UniversityDTO> result = universityService.getAllUniversities();
        log.info("getAllUniversities() UniversityController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<UniversityDTO> createUniversity(@RequestBody UniversityRequest university) {
        log.info("createUniversity() UniversityController start | {}", university);
        UniversityDTO result = universityService.createUniversity(university);
        log.info("createUniversity() UniversityController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<UniversityDTO> updateUniversity(@PathVariable UUID id, @RequestBody UniversityRequest university) {
        log.info("updateUniversity() UniversityController start | {}", university);
        UniversityDTO result = universityService.updateUniversity(id, university);
        log.info("updateUniversity() UniversityController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUniversity(@PathVariable UUID id) {
        log.info("deleteUniversity() UniversityController start | {}", id);
        boolean result = universityService.deleteUniversity(id);
        log.info("deleteUniversity() UniversityController end | {}", result);
        return ResponseEntity.ok().body(result);
    }
}
