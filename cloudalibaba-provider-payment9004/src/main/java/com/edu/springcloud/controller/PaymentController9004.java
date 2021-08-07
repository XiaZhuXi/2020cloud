package com.edu.springcloud.controller;

import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentController9004 {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "randcode1"));
        hashMap.put(2L, new Payment(2L, "randcode2"));
        hashMap.put(3L, new Payment(3L, "randcode3"));
    }

    @GetMapping(value = "/paymentsql/{id}")
    public CommonResult<Payment> paymentCommonResult(@PathVariable("id") Long id) {
        Payment payment=hashMap.get(id);
        CommonResult result = new CommonResult(200, "from mysql server port" + serverPort, payment);
       return result;
    }
}
