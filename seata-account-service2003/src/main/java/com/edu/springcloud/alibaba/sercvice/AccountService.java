package com.edu.springcloud.alibaba.sercvice;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {
    void  decrease(@RequestParam("userId")Long userId, @RequestParam("money") BigDecimal money);

}
