package com.edu.springcloud.service.impl;

import com.edu.springcloud.dao.OrderDao;
import com.edu.springcloud.domain.Order;
import com.edu.springcloud.service.AccountService;
import com.edu.springcloud.service.OrderService;
import com.edu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j

public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService ;
    @Override
    @GlobalTransactional(name = "transact-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("<<<<----开始创建订单---->>>>");
        orderDao.create(order);
        log.info("<<<<---订单微服务开始调用库存，扣减商品--->>>>");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("<<<<---订单微服务开始调用库存，扣减商品end--->>>>");
        log.info("<<<<<----账户微服务开始调用余额，扣减余额--->>>>");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("<<<<<----账户微服务开始调用余额，扣减余额end--->>>>");

        //修改订单的状态  从 0 到  1
        log.info("<<<<<----订单微服务开始调用修改订单状态--->>>>");
        orderDao.update(order.getUserId(),0);
        log.info("<<<<<----订单微服务开始调用修改订单状态end--->>>>");

        log.info("<<<<----下订单end---->>>>");
    }

    @Override
    public void update(Long userId, Integer status) {

    }
}
