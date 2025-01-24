package com.kalocs.internhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterRequest {
    private String name;
    private String email;
    private String position;
    private String phone;
    private String avtUrl;
    private String companyId;
}
