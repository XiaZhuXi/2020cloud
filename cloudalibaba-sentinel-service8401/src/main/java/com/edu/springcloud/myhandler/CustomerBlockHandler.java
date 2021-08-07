package com.edu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.edu.springcloud.entities.CommonResult;
import com.edu.springcloud.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException blockException) {
        return new CommonResult(200, "按照customer全局限流测试", new Payment(2020L, "serial003"));
    }

    public static CommonResult handlerException2(BlockException blockException) {
        return new CommonResult(200, "按照customer2全局限流测试", new Payment(2020L, "serial003"));
    }
}
