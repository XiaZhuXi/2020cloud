package com.edu.springcloud.service;

import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import com.edu.springcloud.service.impl.PaymentFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackImpl.class)
public interface PaymentService {
    @GetMapping(value = "/paymentsql/{id}")
    public CommonResult<Payment> paymentCommonResult(@PathVariable("id") Long id) ;
}
