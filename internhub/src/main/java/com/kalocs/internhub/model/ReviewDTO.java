package com.kalocs.internhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private String id;
    private String studentId;
    private String jobId;
    private double rate;
    private String reviewContent;
    private String date;

    private StudentDTO student;
    private JobDTO job;


}
