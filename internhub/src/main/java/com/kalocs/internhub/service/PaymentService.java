package com.kalocs.internhub.service;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    String createOrder(HttpServletRequest request, int amount, String orderInfor);
    String orderReturn(HttpServletRequest request);
}
