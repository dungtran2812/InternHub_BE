package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.StudentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private UUID id;
    private String name;
    private String phone;
    private String major;
    private String avtUrl;
    private double gpa;
    private String resume;
    private StudentStatus status;
}
