package com.kalocs.internhub.payload.response;

import com.kalocs.internhub.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseModel {
    private String token;
    private String type = "Bearer";
    private UserDTO user;
}
