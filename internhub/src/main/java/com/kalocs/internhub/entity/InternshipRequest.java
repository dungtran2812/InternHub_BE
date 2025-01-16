package com.kalocs.internhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "internship_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipRequest {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
}
