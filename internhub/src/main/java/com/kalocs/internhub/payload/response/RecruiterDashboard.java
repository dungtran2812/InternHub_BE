package com.kalocs.internhub.payload.response;

import com.kalocs.internhub.model.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterDashboard {
    private int applicationCount;
    private int jobCount;
    private CompanyDTO company;
}
