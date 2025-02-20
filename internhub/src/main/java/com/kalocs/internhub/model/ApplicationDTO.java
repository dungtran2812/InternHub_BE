package com.kalocs.internhub.model;

import com.kalocs.internhub.common.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private UUID id;
    private BigInteger date;
    private ApplicationStatus status;
    private String resume;
    private String coverLetter;
    private StudentDTO student;
    private JobDTO job;
}
