package com.edu.springcloud.controller;

import com.edu.springcloud.dao.PaymentDao;
import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentDao paymentDao;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/payment/create")

    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentDao.create(payment);
        log.info("******插入结果：" + i);
        if (i > 0) {
            return new CommonResult(200, "插入成功 ,serverPort:" + serverPort, i);
        } else {
            return new CommonResult(444, "插入数据失败 ,serverPort" + serverPort, null);
        }
    }

    @RequestMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        log.info("******插入结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有数据查询失败，ID为" + id, null);
        }
    }

    @RequestMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****element**" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\n" + instance.getHost() + "\n" + instance.getPort() + "\n" + instance.getUri());
        }
        return this.discoveryClient;
    }

    //    feign超时演示
    @RequestMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    //    sleuth 演示
    @RequestMapping(value = "/payment/zipkin")
    public String zipkin() {
       return "hello payment zipkin";
    }
}
