package com.edu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping(value = "/testS")
    public String testSentinel() {
        return "Hello sentinel serveport  S";
    }
    @GetMapping(value = "/testG")
    public String testSentinelG() {
        log.info(Thread.currentThread().getName() + "\t" + "testG");
        return "Hello sentinel serveport  G";
    }
    @GetMapping(value = "/testD")
    public String testSentinelD() {
        int a = 10 / 0;
        return "Hello sentinel serveport  D";
    }
    @GetMapping(value = "/testF")
    public String testSentinelF() {
        log.info("测试异常数------");
        int a = 10 / 0;
        return "Hello sentinel serveport  F";
    }
    @GetMapping(value = "/testHostKey")
    @SentinelResource(value = "testHostKey",blockHandler = "dealHandler_testHostKey")
    public String testSentinelTestHostKey(@RequestParam (value = "p1",required = false)String p1,
                                          @RequestParam(value = "p2",required = false)String p2) {
        return "Hello sentinel servePort  testHostKey";
    }
    public String dealHandler_testHostKey(String p1, String p2, BlockException blockException){
        return "deal_HostKeyFallback******";
    }
}
