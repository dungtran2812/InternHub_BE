package com.kalocs.internhub.payload.request.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
}
