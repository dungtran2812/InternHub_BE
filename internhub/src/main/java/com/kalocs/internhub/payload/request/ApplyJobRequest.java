package com.kalocs.internhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyJobRequest {
    private String jobId;
    private String resume;
    private String coverLetter;
}
