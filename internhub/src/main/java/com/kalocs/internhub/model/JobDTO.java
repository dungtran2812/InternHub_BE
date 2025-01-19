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
    private String name;
    private String description;
    private String requirement;
    private String type;
    private String duration;
    private int quantity;
    private JobStatus status;
}
