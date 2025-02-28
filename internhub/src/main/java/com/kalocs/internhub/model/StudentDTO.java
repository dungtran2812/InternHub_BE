package com.kalocs.internhub.model;

import com.kalocs.internhub.common.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends UserDTO {
    private String phone;
    private String major;
    private String avtUrl;
    private boolean gender;
    private double gpa;
    private String resume;
    private StudentStatus status;
}
