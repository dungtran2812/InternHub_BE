package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.TransactionBusiness;
import com.kalocs.internhub.entity.Transaction;
import com.kalocs.internhub.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionBusinessImpl extends BaseBusinessImpl<Transaction, TransactionRepository> implements TransactionBusiness {
    @Autowired
    public TransactionBusinessImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }
}
