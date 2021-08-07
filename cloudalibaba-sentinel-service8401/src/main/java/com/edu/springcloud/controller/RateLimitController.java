package com.edu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;
import com.edu.springcloud.myhandler.CustomerBlockHandler;
import com.sun.xml.internal.fastinfoset.CommonResourceBundle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handlerException")
    public CommonResult byResource() {
        return new CommonResult(200, "按照资源名限流测试", new Payment(2020L, "serial001"));
    }

    public CommonResult handlerException(BlockException blockException) {
        return new CommonResult(440, blockException.getClass().getCanonicalName() + "\t" + "服务不可用");
    }

    @GetMapping(value = "/byURL")
    @SentinelResource(value = "byURL")
    public CommonResult byURL() {
        return new CommonResult(200, "按照URL限流测试", new Payment(2020L, "serial002"));
    }

    //    customerhandler
    @GetMapping(value = "/customerhandler")
    @SentinelResource(value = "customerhandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler =
            "handlerException2")
    public CommonResult customerhandler() {
        return new CommonResult(200, "按照customer限流测试", new Payment(2020L, "serial003"));
    }
}
