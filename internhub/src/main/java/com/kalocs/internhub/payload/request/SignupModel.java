package com.kalocs.internhub.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupModel {
    @NotBlank
    String fullName;
    @NotBlank
    String password;
    @Email
    @NotBlank
    String email;
}
