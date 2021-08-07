package com.edu.springcloud.controller;


import com.edu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
    @Value("$server.port")
    private String serverPort;

    @RequestMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @RequestMapping("/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentTimeout_OK(id);
    }

//    服务熔断
@RequestMapping("/payment/circuit/{id}")
public String paymentCircuitBreak(@PathVariable("id") Integer id) {
    String s = paymentHystrixService.paymentCircuitBreaker(id);
    log.info("-------result::"+s);
    return s;
}
}
