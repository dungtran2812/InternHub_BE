package com.kalocs.internhub.model;

import com.kalocs.internhub.common.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String id;
    private String name;
    private String phone;
    private String major;
    private String avtUrl;
    private double gpa;
    private String resume;
    private StudentStatus status;
}
