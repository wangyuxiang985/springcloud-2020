package com.yuxiang.springcloud.service.impl;

import com.yuxiang.springcloud.dao.OrderDao;
import com.yuxiang.springcloud.domain.Order;
import com.yuxiang.springcloud.feign.AccountService;
import com.yuxiang.springcloud.feign.StorageService;
import com.yuxiang.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)//全局异常，结合seata解决分布式事务，默认AT模式（两阶段提交）
    public void create(Order order) {
        log.info("开始创建订单");
        orderDao.create(order);

        log.info("订单微服务，开始调用库存微服务，扣减库存");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("订单微服务，开始调用账户微服务，扣减余款");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("完成，修改订单状态为已完成");
        orderDao.update(order.getUserId(), 0);

        log.info("创建订单完成");
    }
}
