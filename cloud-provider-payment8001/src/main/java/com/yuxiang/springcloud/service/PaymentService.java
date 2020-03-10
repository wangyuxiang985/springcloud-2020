package com.yuxiang.springcloud.service;

import com.yuxiang.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
