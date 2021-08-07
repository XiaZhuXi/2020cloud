package com.edu.springcloud.controller;

import com.edu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
@Resource
    private PaymentHystrixService paymentHystrixService;
    @RequestMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @RequestMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String paymentTimeout_OK(Integer id){
      int a=10/0;
        return "线程池："+Thread.currentThread().getName()+"paymentTimeout_ok"+id+"\t"+"success ";
    }
    public String paymentTimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"消费者80：对方8001系统繁忙，稍后再试"+id+"\t";
    }
    public String payment_Global_FallbackMethod(){
        return "Global error page";
    }
}
