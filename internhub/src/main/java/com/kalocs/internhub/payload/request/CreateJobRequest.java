package com.kalocs.internhub.payload.request;

import com.kalocs.internhub.common.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateJobRequest {
    private String jobTitle;
    private String description;
    private String requirement;
    private String duration;
    private int quantity;
    private String location;
    private JobStatus status;
    private int jobFunctionId;
    private int industryId;
}
