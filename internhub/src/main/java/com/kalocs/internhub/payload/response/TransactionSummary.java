package com.kalocs.internhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionSummary {
    private String description;
    private String date; // yyyy-MM-dd
    private long count;
    private double totalAmount;
}
