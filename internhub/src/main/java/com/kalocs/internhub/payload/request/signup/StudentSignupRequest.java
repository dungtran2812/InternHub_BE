package com.kalocs.internhub.payload.request.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSignupRequest extends SignupRequest{
    private String phone;
    private boolean gender;
    private String major;
}
