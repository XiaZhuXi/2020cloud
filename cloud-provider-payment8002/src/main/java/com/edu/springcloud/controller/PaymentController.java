package com.edu.springcloud.controller;

import com.edu.springcloud.dao.PaymentDao;
import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentDao paymentDao;
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/create")

    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentDao.create(payment);
        log.info("******插入结果：" + i);

        if (i > 0) {
            return new CommonResult(200, "插入成功 ,serverPort:"+serverPort, i);
        } else {
            return new CommonResult(444, "插入数据失败 ,serverPort"+serverPort, null);
        }
    }
    @RequestMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        log.info("******插入结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:"+serverPort, payment);
        } else {
            return new CommonResult(444, "没有数据查询失败，ID为"+id, null);
        }
    }
}
