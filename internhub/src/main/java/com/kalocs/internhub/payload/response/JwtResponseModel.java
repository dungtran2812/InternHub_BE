package com.kalocs.internhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseModel {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String username;
    private String email;
    private String role;
}
