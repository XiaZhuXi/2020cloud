package com.edu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import com.edu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback")//没有配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallBack")//只有fall back配置只管Java业务异常
//    @SentinelResource(value = "fallback",blockHandler= "blockHandler")//只有blockHandler配置只负责sentinel控制台违规异常
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentsql/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数");
        } else if (result.getDate() == null) {
            throw new NullPointerException("NullPointerException ,对应ID没有记录，空指针异常");
        }
        return result;
    }

    //    只有fall back配置
    public CommonResult<Payment> handlerFallBack(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底的异常handlerFallBack内容" + e.getMessage(), payment);
    }

    //    只有blockHandler
    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "blockHandler-sentinel 限流，流水号：" + e.getMessage(), payment);
    }

//    ###############   openfeign
    @Resource
    private PaymentService paymentService;
    @RequestMapping("/consumer/paymentsql/{id}")
    public CommonResult<Payment> paymentCommonResult(@PathVariable("id")Long id){
        return paymentService.paymentCommonResult(id);
    }

}
