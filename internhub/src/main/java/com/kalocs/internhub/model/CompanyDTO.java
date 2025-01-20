package com.kalocs.internhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String id;
    private String name;
    private String address;
    private String industry;
    private String description;
}
