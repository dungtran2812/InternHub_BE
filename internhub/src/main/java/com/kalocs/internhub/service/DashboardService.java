package com.kalocs.internhub.service;

import com.kalocs.internhub.payload.response.DashboardResponse;
import com.kalocs.internhub.payload.response.RevenueDashboard;
import com.kalocs.internhub.payload.response.TransactionSummary;

import java.util.List;

public interface DashboardService {
    DashboardResponse dashboard(long startDate, long endDate);

    List<TransactionSummary> revenue();
}
