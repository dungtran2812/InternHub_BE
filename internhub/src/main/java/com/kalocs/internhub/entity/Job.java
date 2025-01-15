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
    private String name;
    private String description;
    private String requirement;
    private String type;
    private String duration;
    private int quantity;
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private JobCategory category;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
