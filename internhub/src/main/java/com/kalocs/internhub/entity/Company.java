package com.kalocs.internhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
}
