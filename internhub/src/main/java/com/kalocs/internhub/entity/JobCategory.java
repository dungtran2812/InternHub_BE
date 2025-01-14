package com.kalocs.internhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
