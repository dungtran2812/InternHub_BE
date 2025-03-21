package com.kalocs.internhub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.payload.response.ResponseMessage;
import com.kalocs.internhub.service.PaymentService;
import com.kalocs.internhub.service.PremiumService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.payos.PayOS;
import vn.payos.type.Webhook;
import vn.payos.type.WebhookData;

import java.util.UUID;

@RestController
@RequestMapping(URLConstant.PAYMENT)
@Log4j2
@CrossOrigin("*")
public class PaymentController {

    private PaymentService paymentService;
    private final PremiumService premiumService;
    private ModelMapper modelMapper;
    private final PayOS payOS;
    @Value("${internhub.app.front-end-url}")
    private String feUrl;

    @Autowired
    public PaymentController(PaymentService paymentService, PremiumService premiumService, ModelMapper modelMapper, PayOS payOS) {
        this.paymentService = paymentService;
        this.premiumService = premiumService;
        this.modelMapper = modelMapper;
        this.payOS = payOS;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> payment(HttpServletRequest request) {
        log.info("payment() PaymentController start");
        String message = paymentService.createOrder(request, 100, "Mua premium");
        log.info("payment() PaymentController end");
        return ResponseEntity.ok().body(new ResponseMessage(true, message));
    }

    @GetMapping("/vnpay-payment-return")
    public ResponseEntity<Void> paymentRedirect(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String redirect = paymentService.orderReturn(request);
        headers.add("Location", redirect);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("payOS")
    public ResponseEntity<ResponseMessage> createPayOSLink(HttpServletRequest request) {
        log.info("createPayOSLink() PaymentController start");
        String message = paymentService.createPayOSLink(request, 5000);
        log.info("createPayOSLink() PaymentController end");
        return ResponseEntity.ok().body(new ResponseMessage(true, message));
    }

    @PostMapping(path = "/payos-redirect")
    public ResponseEntity<Void> payosTransferHandler(@RequestBody ObjectNode body)
            throws Exception {
        log.info("payosTransferHandler() PaymentController start");
        HttpHeaders headers = new HttpHeaders();
        Webhook webhookBody = modelMapper.map(body, Webhook.class);
        String redirect = feUrl + "/payment/fail";
        if (webhookBody.getSuccess()) {
            //boolean check = premiumService.redirectPayOS(webhookBody);
            redirect = feUrl + "/payment/success";
        }
        headers.add("Location", redirect);
        log.info("payosTransferHandler() PaymentController end");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/payos-redirect")
    public ResponseEntity<Void> handlePaymentRedirect(
            @RequestParam String code,
            @RequestParam String id,
            @RequestParam boolean cancel,
            @RequestParam String status,
            @RequestParam long orderCode) {
        log.info("handlePaymentRedirect() PaymentController start");
        HttpHeaders headers = new HttpHeaders();
        String redirect = premiumService.redirectPayOS(orderCode);
        headers.add("Location", redirect);
        log.info("handlePaymentRedirect() PaymentController end");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
