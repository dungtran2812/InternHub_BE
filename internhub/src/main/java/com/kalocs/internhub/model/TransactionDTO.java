package com.kalocs.internhub.model;

import com.kalocs.internhub.common.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private UUID id;
    private double amount;
    private long createdAt;
    private String description;
    private PaymentStatus status;
}
