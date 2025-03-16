package com.kalocs.internhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalocs.internhub.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private String fullName;
    private UserRole role;
    private UserSubscriptionDTO subscription;

    @JsonProperty("isPremium")
    private boolean getIsPremium() {
        return subscription != null && subscription.getExpiryDate() > Instant.now().toEpochMilli();
    }
}
