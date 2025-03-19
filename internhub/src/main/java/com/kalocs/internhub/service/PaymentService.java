package com.kalocs.internhub.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.payos.type.PaymentData;

public interface PaymentService {
    String createOrder(HttpServletRequest request, int amount, String orderInfo);
    String orderReturn(HttpServletRequest request);

    String createPayOSLink(HttpServletRequest request, int price);
    String createPayOSLink(int price, String orderInfo);

    String createPayOSLink(PaymentData paymentData);
}
