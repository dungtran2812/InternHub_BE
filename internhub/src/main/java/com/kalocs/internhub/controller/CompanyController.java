package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.entity.Company;
import com.kalocs.internhub.model.CompanyDTO;
import com.kalocs.internhub.payload.request.CompanyRequest;
import com.kalocs.internhub.service.CompanyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequestMapping(URLConstant.COMPANY)
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> getCompany(@PathVariable UUID id) {
        log.info("getCompany() CompanyController Start | " + id);
        Company company = companyService.getCompany(id);
        log.info("getCompany() CompanyController End | " + company);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompany() {
        log.info("getAllCompany() CompanyController Start |");
        List<CompanyDTO> companies = companyService.getAllCompanies();
        log.info("getAllCompany() CompanyController End |");
        return ResponseEntity.ok().body(companies);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyRequest company) {
        log.info("createCompany() CompanyController Start | {}", company);
        CompanyDTO createdCompany = companyService.createCompany(company);
        log.info("createCompany() CompanyController End | {}", createdCompany);
        return ResponseEntity.ok().body(createdCompany);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable UUID id, @RequestBody CompanyRequest company) {
        log.info("updateCompany() CompanyController Start | {}", company);
        CompanyDTO updatedCompany = companyService.updateCompany(id, company);
        log.info("updateCompany() CompanyController End | {}", updatedCompany);
        return ResponseEntity.ok().body(updatedCompany);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable UUID id) {
        log.info("deleteCompany() CompanyController Start | {}", id);
        boolean check = companyService.deleteCompany(id);

        if (check) {
            log.info("deleteCompany() CompanyController End | Deleted");
            return ResponseEntity.ok().body("Company deleted successfully");
        } else {
            log.info("deleteCompany() CompanyController End | Not Deleted");
            return ResponseEntity.ok().body("Company cannot be deleted");
        }
    }
}
