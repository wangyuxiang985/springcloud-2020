package com.yuxiang.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    //根据用户扣钱
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
