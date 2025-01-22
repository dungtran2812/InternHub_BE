package com.kalocs.internhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.Manager;

import java.util.List;
import java.util.UUID;

@Table(name = "companies")
@Entity
@Data
public class Company {
    @Id
    private UUID id;
    private String name;
    private String address;
    private String industry;
    private String description;
    private String image;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Recruiter> recruiters;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
}
