package com.kalocs.internhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterDTO {
    private String id;
    private String fullName;
    private String email;
    private String position;
    private String phone;
    private String avtUrl;
    private String backgroundUrl;
    private CompanyDTO company;
}
