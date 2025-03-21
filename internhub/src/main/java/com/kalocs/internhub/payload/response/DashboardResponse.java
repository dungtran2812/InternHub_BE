package com.kalocs.internhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private int jobCount;
    private int applicationCount;
    private int studentCount;
    private int recruiterCount;
    private int subscriptionCount;
    private int totalRevenue;
}
