package com.yuxiang.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yuxiang.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult customerBlockHandler_1(BlockException be) {
        return new CommonResult(444, be.getClass().getCanonicalName() + "\t customerBlockHandler_1");
    }

    public static CommonResult customerBlockHandler_2(BlockException be) {
        return new CommonResult(444, be.getClass().getCanonicalName() + "\t customerBlockHandler_2");
    }
}
