package com.yuxiang.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    //根据商品id扣减指定数量库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
