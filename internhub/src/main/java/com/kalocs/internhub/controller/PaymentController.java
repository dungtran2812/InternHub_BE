package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.payload.response.ResponseMessage;
import com.kalocs.internhub.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstant.PAYMENT)
@Log4j2
@CrossOrigin("*")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> payment(HttpServletRequest request) {
        log.info("payment() PaymentController start");
        String message = paymentService.createOrder(request, 100000, "Mua premium");
        log.info("payment() PaymentController end");
        return ResponseEntity.ok().body(new ResponseMessage(true,message));
    }

    @GetMapping("/vnpay-payment-return")
    public ResponseEntity<Void> paymentRedirect(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String redirect = paymentService.orderReturn(request);
        headers.add("Location", redirect);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
