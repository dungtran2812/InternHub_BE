package com.kalocs.internhub.model;

import com.kalocs.internhub.entity.Industry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String id;
    private String name;
    private String address;
    private String description;
    private String logoCompany;
    private String backgroundCompany;
    private String website;
    private List<IndustryDTO> industries;
}
