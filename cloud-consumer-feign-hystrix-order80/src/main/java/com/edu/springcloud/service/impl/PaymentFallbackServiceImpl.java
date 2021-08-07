package com.edu.springcloud.service.impl;

import com.edu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------PAYMENT-paymentInfo_OK-fallback";
    }

    @Override
    public String paymentTimeout(Integer id) {
        return "------PAYMENT-paymentTimout--fallback";
    }
}
