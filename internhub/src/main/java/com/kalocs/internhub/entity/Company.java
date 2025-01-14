package com.kalocs.internhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

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
