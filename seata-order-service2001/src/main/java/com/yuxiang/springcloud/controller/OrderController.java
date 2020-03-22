package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.domain.CommonResult;
import com.yuxiang.springcloud.domain.Order;
import com.yuxiang.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "创建订单成功");
    }
}
