package com.lxg.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    /**
     * 添加会员
     * @param name 姓名
     * @param age 年龄
     * @return
     */
    @Insert("insert into user values(null,#{name},#{age});")
    public int addUser(@Param("name") String name, @Param("age") Integer age);
}
