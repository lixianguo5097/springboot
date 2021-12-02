package com.lxg.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    /**
     * 添加订单
     * @param number 数量
     * @return
     */
    @Insert("insert into `order` values(null,#{number});")
    public int addOrder(@Param("number") Integer number);
}
