package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.JobStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
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
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String requirement;
    private String duration;
    private int quantity;
    private String location;
    private JobStatus status;
    private String salary;

    @ManyToOne
    @JoinColumn(name = "job_function_id")
    private JobFunction jobFunction;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = true)
    @CreatedDate
    private long createdDate;
    @Column(nullable = true)
    private long updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = Instant.now().toEpochMilli();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = Instant.now().toEpochMilli();
    }
}
