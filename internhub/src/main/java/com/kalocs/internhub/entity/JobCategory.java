package com.kalocs.internhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCategory {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String image;

    @OneToMany(mappedBy = "category")
    private List<Job> jobs;
}
