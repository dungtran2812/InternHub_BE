package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.StudentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {
    private String phone;
    private boolean gender;
    private String major;
    private String avtUrl;
    private double gpa;
    private String resume;
    private StudentStatus status;
}
