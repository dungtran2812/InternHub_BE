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
    @Column(columnDefinition = "TEXT")
    private String description;
    private String logoCompany;
    private String backgroundCompany;
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Recruiter> recruiters;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @ManyToMany
    @JoinTable(
            name = "company_industry",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id")
    )
    private List<Industry> industries;
}
