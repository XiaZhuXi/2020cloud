package com.edu.springcloud.alibaba.controller;

import com.edu.springcloud.alibaba.domain.CommonResult;
import com.edu.springcloud.alibaba.sercvice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,@RequestParam("money") BigDecimal money){
      accountService.decrease(userId, money);
        return new CommonResult(200,"!!!!!!!账户余额扣减成功!!!!!!!");
    }
}
