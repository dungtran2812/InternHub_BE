package com.kalocs.internhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityRequest {
    private UUID id;
    private String name;
    private String website;
    private String phone;
    private String email;
    private String address;
    private String image;
}
