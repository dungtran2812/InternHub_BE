package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.IndustryDTO;
import com.kalocs.internhub.service.IndustryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.INDUSTRY)
@Log4j2
@CrossOrigin("*")
public class IndustryController {
    @Autowired
    private IndustryService industryService;

    @GetMapping()
    public ResponseEntity<List<IndustryDTO>> getAllIndustries() {
        log.info("getAllIndustries() IndustryController start");
        List<IndustryDTO> result = industryService.getAllIndustries();
        log.info("getAllIndustries() IndustryController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustryDTO> getIndustryById(@PathVariable Integer id) {
        log.info("getIndustryById() IndustryController start | id: {}", id);
        IndustryDTO result = industryService.getIndustryById(id);
        log.info("getIndustryById() IndustryController end | {}", result);
        return ResponseEntity.ok().body(result);
    }
}
