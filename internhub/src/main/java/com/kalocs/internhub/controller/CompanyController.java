package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.service.CompanyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Log4j2
@RequestMapping(URLConstant.COMPANY)
@CrossOrigin("*")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("{id}")
    public ResponseEntity<Company> getCompany(@PathVariable UUID id) {
        log.info("getCompany() CompanyController Start | " + id);
        Company company = companyService.getCompany(id);
        log.info("getCompany() CompanyController End | " + company);
        return ResponseEntity.ok().body(company);
    }
}
