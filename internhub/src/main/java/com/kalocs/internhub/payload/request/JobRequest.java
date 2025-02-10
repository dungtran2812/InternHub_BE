package com.kalocs.internhub.payload.request;

import com.kalocs.internhub.common.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    private String jobTitle;
    private String description;
    private String requirement;
    private String duration;
    private int quantity;
    private String location;
    private JobStatus status;
    private String jobFunctionId;
    private String industryId;
    private UUID companyId;
}
