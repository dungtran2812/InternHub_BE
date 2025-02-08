package com.kalocs.internhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobFunction {
    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "jobFunction")
    private List<Job> jobs;
}
