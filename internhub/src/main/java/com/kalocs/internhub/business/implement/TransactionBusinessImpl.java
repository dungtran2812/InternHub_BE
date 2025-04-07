package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.TransactionBusiness;
import com.kalocs.internhub.common.PaymentStatus;
import com.kalocs.internhub.entity.Transaction;
import com.kalocs.internhub.payload.response.TransactionSummary;
import com.kalocs.internhub.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionBusinessImpl extends BaseBusinessImpl<Transaction, TransactionRepository> implements TransactionBusiness {
    @Autowired
    public TransactionBusinessImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }

    @Override
    public int getTransactionCount(long startDate, long endDate) {
        try {
            log.debug("getTransactionCount() TransactionBusinessImpl start | startDate: {}, endDate: {}", startDate, endDate);
            int result = repository.countByCreatedAtBetweenAndStatus(startDate, endDate, PaymentStatus.SUCCESSFUL);
            log.debug("getTransactionCount() TransactionBusinessImpl end | result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("getTransactionCount() TransactionBusinessImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public int getTotalRevenue(long startDate, long endDate) {
        try {
            log.debug("getTotalRevenue() TransactionBusinessImpl start | startDate: {}, endDate: {}", startDate, endDate);
            int result = repository.getRevenue(startDate, endDate,PaymentStatus.SUCCESSFUL).intValue();
            log.debug("getTotalRevenue() TransactionBusinessImpl end | result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("getTotalRevenue() TransactionBusinessImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Transaction> getUserTransaction(UUID userId) {
        try {
            log.debug("getUserTransaction() TransactionBusinessImpl start | userId: {}", userId);
            List<Transaction> result = repository.findAllByUserId(userId);
            log.debug("getUserTransaction() TransactionBusinessImpl end | result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("getUserTransaction() TransactionBusinessImpl error | {}", e.getMessage());
            throw e;
        }
    }

    public List<TransactionSummary> getTransactionSummaries() {
        List<Object[]> raw = repository.getTransactionSummaryByDescriptionAndDateStatusOne();

        return raw.stream().map(row -> {
            String description = (String) row[0];
            String date = row[1].toString(); // format yyyy-MM-dd
            long count = ((Number) row[2]).longValue();
            double total = ((Number) row[3]).doubleValue();
            return new TransactionSummary(description, date, count, total);
        }).collect(Collectors.toList());
    }
}
