package com.edu.springcloud.controller;

import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import com.edu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService service;
    @RequestMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id) {
      return service.getPaymentById(id);
    }
    @RequestMapping(value = "/consumer/payment/timeout")
    public String paymentFeignTimeout(){
      return   service.paymentFeignTimeout();
    }
}
