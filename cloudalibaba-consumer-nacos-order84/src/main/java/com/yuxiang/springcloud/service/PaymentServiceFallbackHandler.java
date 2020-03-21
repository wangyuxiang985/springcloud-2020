package com.yuxiang.springcloud.service;

import com.yuxiang.springcloud.entities.CommonResult;
import com.yuxiang.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallbackHandler implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult(444, "Feign调用-兜底异常PaymentServiceFallbackHandler，exception内容" + id);
    }
}
