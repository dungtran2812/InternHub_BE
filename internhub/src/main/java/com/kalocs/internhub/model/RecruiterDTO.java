package com.kalocs.internhub.model;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterDTO extends UserDTO {
    private String position;
    private String phone;
    private String avtUrl;
    private String backgroundUrl;
    private CompanyDTO company;
    private boolean gender;
}
