package com.kalocs.internhub.payload.request;

import com.kalocs.internhub.model.IndustryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    private String name;
    private String address;
    private String description;
    private List<IndustryDTO> industries;
    private String logoCompany;
    private String backgroundCompany;
    private String website;
    private List<String> imageUrls;
}
