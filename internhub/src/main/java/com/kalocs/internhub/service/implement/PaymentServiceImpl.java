package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.common.VNPayProps;
import com.kalocs.internhub.config.VNPAYConfig;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Value("${internhub.app.front-end-url}")
    private String frontEndUrl;
    @Value("${internhub.app.backend-server-url}")
    private String backEndServerUrl;
    private final VNPayProps vnpayProps;

    private final VNPAYConfig vnpayConfig;
    private final PayOS payOS;

    @Autowired
    public PaymentServiceImpl(VNPayProps vnpayProps, VNPAYConfig vnpayConfig, PayOS payOS) {
        this.vnpayProps = vnpayProps;
        this.vnpayConfig = vnpayConfig;
        this.payOS = payOS;
    }


    @Override
    public String createOrder(HttpServletRequest request, int amount, String orderInfo){
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPAYConfig.getRandomNumber(8);
        String vnp_IpAddr = VNPAYConfig.getIpAddress(request);
        String vnp_TmnCode = vnpayProps.getVnpTmnCode();
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount*100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        String urlReturn = backEndServerUrl + "/" + URLConstant.PAYMENT;
        urlReturn += VNPAYConfig.vnp_Returnurl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String salt = vnpayProps.getVnpHashSecret();
        String vnp_SecureHash = VNPAYConfig.hmacSHA512(salt, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPAYConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    @Override
    public String orderReturn(HttpServletRequest request) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = vnpayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            return String.format("%s/payment-redirect?code=%s",frontEndUrl,request.getParameter("vnp_TransactionStatus"));
        } else {
            return String.format("%s/payment-redirect?code=%s",frontEndUrl,"-1");
        }
    }

    @Override
    public String createPayOSLink(HttpServletRequest request, int price) {
        try {
            String currentTimeString = String.valueOf(String.valueOf(new Date().getTime()));
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));


            PaymentData paymentData = PaymentData.builder().orderCode(orderCode).description("Mua premium").amount(price)
                    .returnUrl("http://localhost:8082/api/payment/payos-redirect").cancelUrl("http://localhost:8082/api/payment/cancel").build();

            CheckoutResponseData data = payOS.createPaymentLink(paymentData);
            return data.getCheckoutUrl();
        } catch (Exception e) {
            log.error("createPayOSLink() error: {}", e.getMessage());
            throw new AppException(HttpStatus.NO_CONTENT.value(),"Lỗi tạo link thanh toán");
        }
    }
}
