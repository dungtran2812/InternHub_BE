package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.TransactionDTO;
import com.kalocs.internhub.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLConstant.TRANSACTION)
@Log4j2
@CrossOrigin("*")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<TransactionDTO>> getUserTransaction() {
        log.info("getUserTransaction() TransactionController start");
        List<TransactionDTO> result = transactionService.getUserTransaction();
        log.info("getUserTransaction() TransactionController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable String id) {
        log.info("getTransactionById() TransactionController start | id: {}", id);
        TransactionDTO result = transactionService.getTransactionById(id);
        log.info("getTransactionById() TransactionController end | {}", result);
        return ResponseEntity.ok().body(result);
    }
}
