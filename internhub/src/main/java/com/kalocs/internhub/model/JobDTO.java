package com.kalocs.internhub.model;

import com.kalocs.internhub.common.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private String id;
    private String jobTitle;
    private String description;
    private String requirement;
    private String duration;
    private int quantity;
    private String location;
    private String salary;
    private JobStatus status;
    private JobFunctionDTO jobFunction;
    private IndustryDTO industry;
    private CompanyDTO company;
}
