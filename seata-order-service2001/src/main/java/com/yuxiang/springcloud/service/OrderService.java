package com.yuxiang.springcloud.service;

import com.yuxiang.springcloud.domain.Order;

public interface OrderService {
    //创建订单
    void create(Order order);
}
