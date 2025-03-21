package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.TransactionBusiness;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Transaction;
import com.kalocs.internhub.model.TransactionDTO;
import com.kalocs.internhub.service.TransactionService;
import com.kalocs.internhub.utils.AuthUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {

    private final TransactionBusiness transactionBusiness;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionServiceImpl(TransactionBusiness transactionBusiness, ModelMapper modelMapper) {
        this.transactionBusiness = transactionBusiness;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<TransactionDTO> getUserTransaction() {
        try {
            log.debug("getUserTransaction() TransactionServiceImpl start");
            UUID userId = AuthUtils.getCurrentUserId();
            List<TransactionDTO> result = transactionBusiness.getUserTransaction(userId).stream().map(transaction -> modelMapper.map(transaction, TransactionDTO.class)).toList();
            log.debug("getUserTransaction() TransactionServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getUserTransaction() TransactionServiceImpl error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public TransactionDTO getTransactionById(String id) {
        try {
            log.debug("getTransactionById() TransactionServiceImpl start | id: {}", id);
            Transaction transaction = transactionBusiness.getById(UUID.fromString(id)).orElseThrow(() -> new AppException(404, "Không tìm thấy giao dịch"));
            if (!transaction.getUser().getId().equals(AuthUtils.getCurrentUserId())) {
                throw new AppException(403, "Không có quyền truy cập");
            }
            TransactionDTO result = modelMapper.map(transaction, TransactionDTO.class);
            log.debug("getTransactionById() TransactionServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getTransactionById() TransactionServiceImpl error: {}", e.getMessage());
            throw e;
        }
    }

}
