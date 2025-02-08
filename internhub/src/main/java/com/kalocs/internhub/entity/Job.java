package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.JobStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    private UUID id;
    private String jobTitle;
    private String description;
    private String requirement;
    private String duration;
    private int quantity;
    private String location;
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "job_function_id")
    private JobFunction jobFunction;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
