package com.kalocs.internhub.service;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    String createOrder(HttpServletRequest request, int amount, String orderInfo);
    String orderReturn(HttpServletRequest request);

    String createPayOSLink(HttpServletRequest request, int price);
}
