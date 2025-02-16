package com.kalocs.internhub.payload.request.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterSignupRequest extends SignupRequest{
    private String address;
    private String phone;
    private UUID companyId;
}
