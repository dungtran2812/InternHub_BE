package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Transaction;
import com.kalocs.internhub.payload.response.TransactionSummary;

import java.util.List;
import java.util.UUID;

public interface TransactionBusiness extends BaseBusiness<Transaction> {
    int getTransactionCount(long startDate, long endDate);

    int getTotalRevenue(long startDate, long endDate);

    List<Transaction> getUserTransaction(UUID userId);

    List<TransactionSummary> getTransactionSummaries();
}
