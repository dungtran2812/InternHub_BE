package com.kalocs.internhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "recruiters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String position;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
