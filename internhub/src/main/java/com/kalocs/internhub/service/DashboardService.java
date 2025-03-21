package com.kalocs.internhub.service;

import com.kalocs.internhub.payload.response.DashboardResponse;

public interface DashboardService {
    DashboardResponse dashboard(long startDate, long endDate);
}
