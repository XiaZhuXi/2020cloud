package com.edu.springcloud.service;

import com.edu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    //   1 新建订单
    void create(Order order);
    //   2  修改订单状态从零到1
    void update(@Param("userId")Long userId, @Param("sataus")Integer status);
}
