package com.edu.springcloud.alibaba.sercvice.impl;

import com.edu.springcloud.alibaba.dao.AccountDao;
import com.edu.springcloud.alibaba.sercvice.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
@Resource
private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {

        accountDao.decrease(userId, money);
    }
}
