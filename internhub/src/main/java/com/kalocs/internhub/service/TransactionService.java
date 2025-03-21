package com.kalocs.internhub.service;

import com.kalocs.internhub.model.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getUserTransaction();
    TransactionDTO getTransactionById(String id);
}
