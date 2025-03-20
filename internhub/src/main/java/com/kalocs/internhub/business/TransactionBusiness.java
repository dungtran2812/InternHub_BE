package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.Transaction;

public interface TransactionBusiness extends BaseBusiness<Transaction> {
    int getTransactionCount(long startDate, long endDate);

    int getTotalRevenue(long startDate, long endDate);
}
