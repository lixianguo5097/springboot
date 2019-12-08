package com.lxg.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    @Insert("insert into order values(null,#{number});")
    public int addOrder(@Param("number") String number);
}
