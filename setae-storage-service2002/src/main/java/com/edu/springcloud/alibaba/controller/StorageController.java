package com.edu.springcloud.alibaba.controller;

import com.edu.springcloud.alibaba.domain.CommonResult;
import com.edu.springcloud.alibaba.sercvice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"!!!!!!!库存扣减成功!!!!!!!");
    }
}
