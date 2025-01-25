package com.kalocs.internhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "universities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class University extends User {
    @Column(columnDefinition = "TEXT")
    private String website;
    private String phone;
    private String address;
    private String image;
}
