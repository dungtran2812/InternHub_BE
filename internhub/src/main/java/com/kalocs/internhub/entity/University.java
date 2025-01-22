package com.kalocs.internhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "universities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class University {
    @Id
    private UUID id;
    private String name;
    private String website;
    private String phone;
    private String email;
    private String address;
    private String image;
}
