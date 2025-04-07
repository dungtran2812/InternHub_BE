package com.kalocs.internhub.repository;

import com.kalocs.internhub.common.PaymentStatus;
import com.kalocs.internhub.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,UUID> {
    int countByCreatedAtBetweenAndStatus(long startDate, long endDate, PaymentStatus status);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.createdAt BETWEEN :startDate AND :endDate AND t.status = :status")
    Double getRevenue(@Param("startDate") long startDate, @Param("endDate") long endDate, @Param("status") PaymentStatus status);

    List<Transaction> findAllByUserId(UUID userId);

    @Query(value = """
    SELECT 
        t.description,
        TO_TIMESTAMP(t.created_at / 1000)::date AS txn_date,
        COUNT(*) AS count,
        SUM(t.amount) AS total_amount
    FROM transactions t
    WHERE t.status = 1
    GROUP BY t.description, txn_date
    ORDER BY txn_date DESC
    """, nativeQuery = true)
    List<Object[]> getTransactionSummaryByDescriptionAndDateStatusOne();
}
