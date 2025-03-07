package com.kalocs.internhub.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank
    private String fullName;
    @Email
    private String email;
    private String phone;
    private boolean gender;
    private String major;
    private String avtUrl;
    private double gpa;
}
