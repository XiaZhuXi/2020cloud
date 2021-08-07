package com.edu.springcloud.service.impl;

import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import com.edu.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackImpl implements PaymentService {
    @Override
    public CommonResult<Payment> paymentCommonResult(Long id) {
        return new CommonResult<>(454,"服务降级返回-------PaymentFallbackImpl-----",new Payment(id,"errorservice"));
    }
}
