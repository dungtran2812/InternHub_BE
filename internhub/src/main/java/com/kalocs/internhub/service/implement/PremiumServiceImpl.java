package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.PremiumPlanBusiness;
import com.kalocs.internhub.business.TransactionBusiness;
import com.kalocs.internhub.business.UserBusiness;
import com.kalocs.internhub.business.UserSubscriptionBusiness;
import com.kalocs.internhub.common.PaymentStatus;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.config.security.services.UserDetailsImpl;
import com.kalocs.internhub.entity.PremiumPlan;
import com.kalocs.internhub.entity.Transaction;
import com.kalocs.internhub.entity.User;
import com.kalocs.internhub.entity.UserSubscription;
import com.kalocs.internhub.service.PaymentService;
import com.kalocs.internhub.service.PremiumService;
import com.kalocs.internhub.utils.AuthUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.type.*;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@Log4j2
public class PremiumServiceImpl implements PremiumService {

    private final UserSubscriptionBusiness userSubscriptionBusiness;
    private final PremiumPlanBusiness premiumPlanBusiness;
    private final TransactionBusiness transactionBusiness;
    private final UserBusiness userBusiness;
    private final PaymentService paymentService;
    private final PayOS payOS;
    private final ModelMapper modelMapper;
    private final CacheService cacheService;

    @Value("${internhub.app.front-end-url}")
    private String frontEndUrl;
    @Value("${internhub.app.backend-server-url}")
    private String backEndServerUrl;

    @Autowired
    public PremiumServiceImpl(UserSubscriptionBusiness userSubscriptionBusiness, PremiumPlanBusiness premiumPlanBusiness, TransactionBusiness transactionBusiness, UserBusiness userBusiness, PaymentService paymentService, PayOS payOS, ModelMapper modelMapper, CacheService cacheService) {
        this.userSubscriptionBusiness = userSubscriptionBusiness;
        this.premiumPlanBusiness = premiumPlanBusiness;
        this.transactionBusiness = transactionBusiness;
        this.userBusiness = userBusiness;
        this.paymentService = paymentService;
        this.payOS = payOS;
        this.modelMapper = modelMapper;
        this.cacheService = cacheService;
    }

    @Override
    public String byPremium(UUID id) {
        try {
            log.info("byPremium() PremiumServiceImpl start");
            User user = userBusiness.getUserByEmail(AuthUtils.getCurrentUser().getEmail());
            PremiumPlan premiumPlan = premiumPlanBusiness.getById(id).orElseThrow(() -> new AppException(404, "Không tìm thấy gói premium"));
            if (!user.getRole().equals(premiumPlan.getRole())) {
                throw new AppException(400, "Gói premium không phù hợp với role của bạn");
            }
            //Payment Data Initialization
            String currentTimeString = String.valueOf(String.valueOf(new Date().getTime()));
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));
            String orderInfo = premiumPlan.getName();
            cacheService.cache(String.valueOf(orderCode), premiumPlan.getId().toString());
            String redirectUrl = backEndServerUrl + "/payment/payos-redirect";

            PaymentData paymentData = PaymentData.builder()
                    .orderCode(orderCode)
                    .description(orderInfo)
                    .amount((int) premiumPlan.getPrice())
                    .buyerEmail(user.getEmail())
                    .returnUrl(redirectUrl)
                    .cancelUrl(redirectUrl)
                    .build();
            paymentData.addItem(ItemData.builder().name(premiumPlan.getName()).quantity(1).price((int) premiumPlan.getPrice()).build());
            UUID transactionId = UUID.nameUUIDFromBytes(String.valueOf(orderCode).getBytes());
            Transaction transaction = new Transaction(transactionId, user, premiumPlan.getPrice(), Instant.now().toEpochMilli(), orderInfo, PaymentStatus.PENDING);
            transactionBusiness.create(transaction);
            String link = paymentService.createPayOSLink(paymentData);
            log.info("byPremium() PremiumServiceImpl end");
            return link;
        } catch (Exception e) {
            log.error("byPremium() PremiumServiceImpl error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public String redirectPayOS(long orderCode) {
        try {
            log.info("redirectPayOS() PremiumServiceImpl start");
            UUID transactionId = UUID.nameUUIDFromBytes(String.valueOf(orderCode).getBytes());
            PaymentLinkData paymentLinkData = payOS.getPaymentLinkInformation(orderCode);
            if (paymentLinkData == null) {
                throw new AppException(404, "Không tìm thấy thông tin thanh toán");
            }
            if (!paymentLinkData.getStatus().equals("PAID")) {
                throw new AppException(400, "Thanh toán không thành công");
            }
            Transaction transaction = transactionBusiness.getById(transactionId).orElseThrow(() -> new AppException(404, "Không tìm thấy giao dịch"));
            if (transaction.getStatus().equals(PaymentStatus.SUCCESSFUL)) {
                return frontEndUrl + "/payment/success?id=" + transactionId;
            }
            UUID premiumPlanId = UUID.fromString(cacheService.get(String.valueOf(orderCode)));
            PremiumPlan premiumPlan = premiumPlanBusiness.getById(premiumPlanId).orElseThrow(() -> new AppException(404, "Không tìm thấy gói premium"));
            if (transaction.getStatus().equals(PaymentStatus.PENDING)) {
                transaction.setStatus(PaymentStatus.SUCCESSFUL);
                transactionBusiness.update(transaction);
                if (transaction.getUser().getSubscription()!= null) {
                    UserSubscription userSubscription = transaction.getUser().getSubscription();
                    userSubscription.setPlan(premiumPlan);
                    if (transaction.getUser().getSubscription().getExpiryDate() > Instant.now().toEpochMilli()) {
                        userSubscription.setStartDate(transaction.getUser().getSubscription().getStartDate());
                        userSubscription.setExpiryDate(transaction.getUser().getSubscription().getExpiryDate() + premiumPlan.getDuration());
                    } else {
                        userSubscription.setStartDate(Instant.now().toEpochMilli());
                        userSubscription.setExpiryDate(Instant.now().plusMillis(premiumPlan.getDuration()).toEpochMilli());
                    }
                    userSubscriptionBusiness.update(userSubscription);
                } else {
                    UserSubscription userSubscription = UserSubscription.builder()
                            .id(transactionId)
                            .user(transaction.getUser())
                            .plan(premiumPlan)
                            .startDate(Instant.now().toEpochMilli())
                            .expiryDate(Instant.now().plusMillis(premiumPlan.getDuration()).toEpochMilli())
                            .build();
                    cacheService.remove(String.valueOf(orderCode));
                    userSubscriptionBusiness.create(userSubscription);
                }
                log.info("redirectPayOS() PremiumServiceImpl end");
                return frontEndUrl + "/payment/success?id=" + transactionId;
            }
            return frontEndUrl + "/payment/fail";
        } catch (Exception e) {
            log.error("redirectPayOS() PremiumServiceImpl error: {}", e.getMessage());
            throw new AppException(500, e.getMessage());
        }
    }
}
